package jp.forkhub;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;


public class HistoryActivityTest  {
    /*public HistoryActivity example;
   @Before
   protected void setUp() throws Exception {
        example=new HistoryActivity();
    }*/
    @org.junit.Test
    public void testRead() throws  Exception {
        HistoryActivity example=new HistoryActivity();
        Class<?> clazz = example.getClass();
        Field field = clazz.getDeclaredField("content");
        field.setAccessible(true);
        String string = (String) field.get(example);

        assertEquals("usersname", string);
        example.read();

        String string1 = (String) field.get(example);
        assertEquals("justisement",string1);
    }
}