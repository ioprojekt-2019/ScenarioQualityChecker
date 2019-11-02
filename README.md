https://travis-ci.com/ioprojekt-2019/ScenarioQualityChecker.svg?branch=develop

# ScenarioQualityChecker
<h2>Example scenario JSON syntax</h2>

```json
{
  "title": "Dodanie książki",
  "actors": ["Bibliotekarz"],
  "systemActor": "System",
  
  "steps": [
      {
        "name": "Bibliotekarz wybiera opcje dodania nowej pozycji książkowej"
      },
      {
        "name": "Wyświetla się formularz."
      },
      {
      	"name": "Bibliotekarz podaje dane książki."
      },
      {
        "name": "IF: Bibliotekarz pragnie dodać egzemplarze książki",
        "steps": [
            {
            	"name": "Bibliotekarz wybiera opcję definiowania egzemplarzy"
            },
            {
            	"name": "System prezentuje zdefiniowane egzemplarze"	
            },
            {
              "name": "FOR EACH egzemplarz:",
              "steps": [
	              	{
	              		"name": "Bibliotekarz wybiera opcję dodania egzemplarza"
	              	},
	            	{
	              		"name": "System prosi o podanie danych egzemplarza"
	              	},
	              	{
	              		"name": "Bibliotekarz podaje dane egzemplarza i zatwierdza."
	              	},
	              	{
	              		"name": "System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy."
	              	}
                ]
            }
          ]
      },
      {
		"name": "Bibliotekarz zatwierdza dodanie książki."
      },
      {
      	"name": "System informuje o poprawnym dodaniu książki."
      }
    ]
}
```