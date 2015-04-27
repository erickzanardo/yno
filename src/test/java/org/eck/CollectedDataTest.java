package org.eck;

import org.eck.exceptions.NoDataCollectedException;
import org.eck.exceptions.NoTableInformedException;
import org.junit.Test;

public class CollectedDataTest {

    @Test(expected = NoTableInformedException.class)
    public void testValidateNoTable() {
        CollectedData collectedData = new CollectedData();
        collectedData.data("bla", 1);
        collectedData.validate();
    }

    @Test(expected = NoDataCollectedException.class)
    public void testValidateNoData() {
        CollectedData collectedData = new CollectedData();
        collectedData.table("bla");
        collectedData.validate();
    }

    @Test
    public void testValidate() {
        CollectedData collectedData = new CollectedData();
        collectedData.table("bla");
        collectedData.data("bla", 1);
        collectedData.validate();
    }

}
