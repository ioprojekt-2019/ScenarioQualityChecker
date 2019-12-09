#include "httpworker.h"
#include <QNetworkAccessManager>
#include <QNetworkReply>
#include <QEventLoop>
#include <QJsonObject>
#include <QJsonArray>

void HttpWorker::setHost(QString addr)
{
    host = addr;
}

void HttpWorker::setPort(int value)
{
    port = value;
}

QString HttpWorker::getHost()
{
    return host;
}

int HttpWorker::getPort()
{
    return port;
}

HttpWorker::HttpWorker(QJsonDocument doc, QString service, QObject *parent) : QThread(parent), errorException("")
{
    this->url.setScheme("http");
    this->url.setHost(host);
    this->url.setPort(port);
    this->url.setPath(service);
    this->sentJson = doc;
    this->error = false;
}

void HttpWorker::run()
{
    if(isJsonEmpty(sentJson.object())) {
        setError("Empty json document.");
        return;
    }

    QNetworkAccessManager manager;
    QEventLoop loop;
    connect(&manager, &QNetworkAccessManager::finished, &loop, &QEventLoop::quit);

    QNetworkRequest request(url);
    request.setHeader(QNetworkRequest::KnownHeaders::ContentTypeHeader, "application/json");

    QNetworkReply *reply = manager.post(request, sentJson.toJson());

    loop.exec();

    receivedJson = QJsonDocument::fromJson(reply->readAll());

    if(reply->error() != QNetworkReply::NetworkError::NoError) {
        setError(reply->errorString() + '\n' + jsonErrorsToString());
    }
}

QString HttpWorker::jsonErrorsToString()
{
    QJsonObject root = receivedJson.object();
    QJsonArray errorsTypes = root["errors"].toArray();

    QString str;
    for(auto val: errorsTypes) {
        str += '-' + val.toString() + '\n';
    }

    return str;
}

void HttpWorker::setError(const QString &msg)
{
    errorException = std::runtime_error(msg.toLatin1());
    error = true;
}

bool HttpWorker::isJsonEmpty(const QJsonObject &json) const
{
    for(auto x: json) {
        switch(x.type()) {
            case QJsonValue::Type::Null:
                return true;

            case QJsonValue::Type::Array:
                if(x.toArray().isEmpty()) {
                    return true;
                }
            break;

            case QJsonValue::Type::Object:
                if(x.toObject().isEmpty() || isJsonEmpty(x.toObject())) {
                    return true;
                }
            break;

            case QJsonValue::Type::String:
                if(x.toString().isEmpty()) {
                    return true;
                }
            break;

            case QJsonValue::Type::Undefined:
                return true;

            default:
            break;
        }
    }

    return false;
}

QJsonDocument HttpWorker::getResponse()
{
    if(error) {
        error = false;
        throw errorException;
    } else {
        return receivedJson;
    }
}
