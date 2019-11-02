package pl.put.poznan.transformer.logic.validatorimpl;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
public class ListOfNonemptyStringsValidatorTest {
    private final ListOfNonemptyStringsValidator listOfNonemptyStringsValidator = new ListOfNonemptyStringsValidator();

    private List<String> inputStringsList;
    private boolean expectedValid;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {true, new ArrayList<String>()},
                {true, Arrays.asList(new String[]{"String 1", "S"})},
                {true, Arrays.asList(new String[]{"S", "S"})},
                {false, Arrays.asList(new String[]{"", "Text"})},
                {false, Arrays.asList(new String[]{"Text", null})},
                {false, Arrays.asList(new String[]{"", null})}
        });
    }

    public ListOfNonemptyStringsValidatorTest(boolean expectedValid, List<String> inputStringsList) {
        this.expectedValid = expectedValid;
        this.inputStringsList = inputStringsList;
    }

    @Test
    public void testIsValidReturnsTrueForListOfNonemptyStrings() {
        Assume.assumeTrue(expectedValid);
        assertTrue(listOfNonemptyStringsValidator.isValid(inputStringsList, null));
    }

    @Test
    public void testIsValidReturnsFalseWhenEmptyStringInList() {
        Assume.assumeFalse(expectedValid);
        assertFalse(listOfNonemptyStringsValidator.isValid(inputStringsList, null));
    }
}
