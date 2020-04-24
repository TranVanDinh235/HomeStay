package com.example.homestay;

import com.example.homestay.utils.CommonUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjLUY9vtQ/OoHsDkA4b29SbY8esENNQTrQ5n7ZqRz+qxTrcybWBjItoW+uQRZC/RThMhsYalO7lUwqYsZ6OWqKC/qYL4s7UH1OMXhZxWCdB/+ujVOV8jLTpB7Mz9nSCYvFN0r1XqCNHcK1iCemGToLPeY/2PgWNu9AdF9NP5lvwUr70u8UtM/CZVs2jzc5Rb6NoCZXKKcggIZo5r1TfCfiBI+n8IOzzBvY3wNIvEWV1uCCMds86lNr6ugMIDD2jLQNX47ZGflP4fmAgWlyG5sb0jGJKmKiajCDZpBA8vVa1/OA6POpI9sMiRdPuUvxWZc8N27eHfBzM997exAOznBIwIDAQAB";

        String d1 = "{\"house_id\":\"1\"," +
                "\"guest_id\":\"1\"," +
                "\"content\":\"This is a great house\"}";
        String d2 = "{\"id\":\"3\"," +
                "\"house_id\":\"1\"," +
                "\"guest_id\":\"1\"," +
                "\"content\":\"Chủ nhà thân thiện, vui tính\"}";

        String data1 = CommonUtils.encrypt(key, d1);
        String data2 = CommonUtils.encrypt(key, d2);
        data1 = CommonUtils.replace(data1);
        data2 = CommonUtils.replace(data2);

        System.out.println(data1);
        System.out.println(data2);

    }
}