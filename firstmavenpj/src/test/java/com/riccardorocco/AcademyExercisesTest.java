package com.riccardorocco;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class AcademyExercisesTest {
    @Test
    public void testAddBigNumbers() {
        assertEquals("2", AcademyExercises.addBigNumbers("1", "1"));
        assertEquals("579", AcademyExercises.addBigNumbers("123", "456"));
        assertEquals("1110", AcademyExercises.addBigNumbers("888", "222"));
        assertEquals("1441", AcademyExercises.addBigNumbers("1372", "69"));
        assertEquals("468", AcademyExercises.addBigNumbers("12", "456"));
        assertEquals("201", AcademyExercises.addBigNumbers("100", "101"));
        assertEquals("91002328220491911630239667963", AcademyExercises.addBigNumbers("63829983432984289347293874", "90938498237058927340892374089"));
    }
    @Test
    public void testAllAlone() {
      char[][] house = {
        "  o                o        #######".toCharArray(),
        "###############             #     #".toCharArray(),
        "#             #        o    #     #".toCharArray(),
        "#  X          ###############     #".toCharArray(),
        "#                                 #".toCharArray(),
        "###################################".toCharArray()
      };
      assertEquals(true, AcademyExercises.allAlone(house));
      house = new char[][]{
        "#################             ".toCharArray(),
        "#     o         #   o         ".toCharArray(),
        "#          ######        o    ".toCharArray(),
        "####       #                  ".toCharArray(),
        "   #       ###################".toCharArray(),
        "   #                         #".toCharArray(),
        "   #                  X      #".toCharArray(),
        "   ###########################".toCharArray()
      };
      assertEquals(false, AcademyExercises.allAlone(house));  
    }
  
    @Test
	public void testRemoveNb() {
		List<long[]> res = new ArrayList<long[]>();
		res.add(new long[] {15, 21});
		res.add(new long[] {21, 15});
		List<long[]> a = AcademyExercises.removNb(26);
		assertArrayEquals(res.get(0), a.get(0));
		assertArrayEquals(res.get(1), a.get(1));
        res = new ArrayList<long[]>();
		a = AcademyExercises.removNb(100);
		assertTrue(res.size() == a.size());
	}
    @Test
    public void testInterpret() {
        String[] program = new String[] { "mov a 5", "inc a", "dec a", "dec a", "jnz a -1", "inc a" };
        Map<String, Integer> out = new HashMap<String, Integer>();
        out.put("a", 1);
        assertEquals(out, AcademyExercises.interpret(program));
        program = new String[] { "mov a -10", "mov b a", "inc a", "dec b", "jnz a -2" };
        out = new HashMap<String, Integer>();
        out.put("a", 0);
        out.put("b", -20);
        assertEquals(out, AcademyExercises.interpret(program));
    }
    @Test
	public void testGetFriendDateAndTime() throws ParseException {
		assertEquals("27-03-2021 21:41", AcademyExercises.getFriendDateAndTime("27-03-2021 19:41", "Europe/Warsaw", "Africa/Asmera"));
		assertEquals("28-03-2021 05:41", AcademyExercises.getFriendDateAndTime("27-03-2021 19:41", "Europe/Warsaw", "Australia/Sydney"));
		assertEquals("28-03-2021 10:41", AcademyExercises.getFriendDateAndTime("28-03-2021 19:41", "Australia/Sydney", "Europe/Warsaw"));
		assertEquals("19-03-2021 12:01", AcademyExercises.getFriendDateAndTime("19-03-2021 01:01", "America/Halifax", "Asia/Makassar"));
		assertEquals("01-12-2021 00:00", AcademyExercises.getFriendDateAndTime("01-12-2021 00:00", "Europe/Dublin", "Europe/Belfast"));
	}
    @Test
    public void testOrder(){
        assertEquals(AcademyExercises.order("is2 Thi1s T4est 3a"),  "Thi1s is2 3a T4est");
        assertEquals(AcademyExercises.order("4of Fo1r pe6ople g3ood th5e the2"), "Fo1r the2 g3ood 4of th5e pe6ople");
        assertEquals("Empty input should return empty string", AcademyExercises.order(""), "");
    }
    @Test
    public void testSortCsvColums() {
        String preSorting = "myjinxin2015;raulbc777;smile67;Dentzil;SteffenVogel_79\n"
                + "17945;10091;10088;3907;10132\n"
                + "2;12;13;48;11";
        String postSorting = "Dentzil;myjinxin2015;raulbc777;smile67;SteffenVogel_79\n"
                + "3907;17945;10091;10088;10132\n"
                + "48;2;12;13;11";
        assertEquals(postSorting, AcademyExercises.sortCsvColumns(preSorting));
        assertEquals(postSorting, AcademyExercises.sortCsvColumnsII(preSorting));
      
        preSorting = "IronMan;Thor;Captain America;Hulk\n"
                + "arrogant;divine;honorably;angry\n"
                + "armor;hammer;shield;greenhorn\n"
                + "Tony;Thor;Steven;Bruce";
        postSorting = "Captain America;Hulk;IronMan;Thor\n"
                + "honorably;angry;arrogant;divine\n"
                + "shield;greenhorn;armor;hammer\n"
                + "Steven;Bruce;Tony;Thor";
        assertEquals(postSorting, AcademyExercises.sortCsvColumns(preSorting));
        assertEquals(postSorting, AcademyExercises.sortCsvColumnsII(preSorting));
    }

    @Test
    public void testTheGame(){
        ArrayList<String> words = new ArrayList<String>(Arrays.asList("ab","bc","","de","","",""));
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("ab","bc"));
        assertEquals(expected, AcademyExercises.theGame(words));
    }
    @Test
    public void testCountOddPentaFib() {
        assertEquals(3, AcademyExercises.countOddPentaFibList(10));
        assertEquals(5, AcademyExercises.countOddPentaFibArray(15));
        System.out.println("Fixed Tests: countOddPentaFib, low values");    
        long[] lstI = new long[] {45, 68, 76, 100, 121};
        long[] resultsI = new long[] {15, 23, 25, 33, 40};
        for (int i = 0; i <= 4; i++) {
            testingFib(AcademyExercises.countOddPentaFibList(lstI[i]), resultsI[i]);
        }
    }
    private static void testingFib(long actual, long expected) {
        assertEquals(expected, actual);
    }

    @Test
    public void testCreatePhoneNumber() {
        assertEquals("(123) 456-7890", AcademyExercises.createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}));
        assertEquals("(111) 111-1111", AcademyExercises.createPhoneNumber(new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
        assertEquals("(478) 157-9971", AcademyExercises.createPhoneNumber(new int[] {4, 7, 8, 1, 5, 7, 9, 9, 7, 1}));
        assertEquals("(780) 221-7513", AcademyExercises.createPhoneNumber(new int[] {7, 8, 0, 2, 2, 1, 7, 5, 1, 3}));
    }

    @Test
    public void testCreateSprial(){
        int[][] expected = new int[][]{
            {1, 2, 3},
            {8, 9, 4},
            {7, 6, 5}};    
        Assert.assertArrayEquals(expected, AcademyExercises.createSpiral(3));
        expected = new int[][]{
            {1, 2, 3, 4, 5},
            {16, 17, 18, 19, 6},
            {15, 24, 25, 20, 7},
            {14, 23, 22, 21, 8},
            {13, 12, 11, 10, 9}};
        Assert.assertArrayEquals(expected, AcademyExercises.createSpiral(5));
    }

    @Test
    public void testTenMinWalk(){
        assertEquals("Should return true", true, AcademyExercises.tenMinWalk(new char[] {'n','s','n','s','n','s','n','s','n','s'}));
        assertEquals("Should return false", false, AcademyExercises.tenMinWalk(new char[] {'w','e','w','e','w','e','w','e','w','e','w','e'}));
        assertEquals("Should return false", false, AcademyExercises.tenMinWalk(new char[] {'w'}));
        assertEquals("Should return false", false, AcademyExercises.tenMinWalk(new char[] {'n','n','n','s','n','s','n','s','n','s'}));
    }

    @Test
    public void testLargestPairSum(){
        assertEquals("Failed test case [10, 14, 2, 23, 19]:", 42, AcademyExercises.largestPairSum(new int[]{10,14,2,23,19}));
        assertEquals("Failed test case [-100, -29, -24, -19, 19]:", 0, AcademyExercises.largestPairSum(new int[]{-100,-29,-24,-19,19}));
        assertEquals("Failed test case [1, 2, 3, 4, 6, -1, 2]:", 10, AcademyExercises.largestPairSum(new int[]{1,2,3,4,6,-1,2}));
        assertEquals("Failed test case [-10, -8, -16, -18, -19]:", -18, AcademyExercises.largestPairSum(new int[]{-10,-8,-16,-18,-19}));
    }

    @Test
    public void testDuplicateCount() {
        assertEquals(0, AcademyExercises.duplicateCount("abcde"));
        assertEquals(1, AcademyExercises.duplicateCount("abcdea"));
        assertEquals(1, AcademyExercises.duplicateCount("indivisibility"));
        assertEquals(2, AcademyExercises.duplicateCount("Indivisibilities"));
        String testThousandA = new String(new char[1000]).replace('\0', 'a');
        String testHundredB = new String(new char[100]).replace('\0', 'b');
        String testTenC = new String(new char[10]).replace('\0', 'c');
        String test1CapitalA = new String(new char[1]).replace('\0', 'A'); 
        String test1d = new String(new char[1]).replace('\0', 'd'); 
        String test = test1d + test1CapitalA + testTenC + testHundredB + testThousandA;
        assertEquals(3, AcademyExercises.duplicateCount(test));
    }

    @Test
    public void testMovie() { 
        testing(AcademyExercises.movie(500, 15, 0.9), 43);
        testing(AcademyExercises.movie(100, 10, 0.95), 24);
        testing(AcademyExercises.movie(0, 10, 0.95), 2);
        testing(AcademyExercises.movie(250, 20, 0.9), 21);
        testing(AcademyExercises.movie(500, 20, 0.9), 34);
        testing(AcademyExercises.movie(2500, 20, 0.9), 135);
    }
    private static void testing(long actual, long expected) {
        assertEquals(expected, actual);
    }

    @Test
    public void testHighAndLow(){
        assertEquals("42 -9", AcademyExercises.highAndLow("8 3 -5 42 -1 0 0 -9 4 7 4 -4"));
        assertEquals("3 1", AcademyExercises.highAndLow("1 2 3"));
        assertEquals("42 42", AcademyExercises.highAndLow("42 42"));

        Random r = new Random();
        final int NUM_RANDOM_TESTS = 10;
        final int MAX_X = 1000;
    
        for(int i = 0; i < NUM_RANDOM_TESTS; i++) {
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            for(int o = 0; o < r.nextInt(10) + 5; o++)
            numbers.add(r.nextInt(MAX_X * 2) - MAX_X);
            String s = numbers.get(0).toString();
            for(int o = 1; o < numbers.size(); o++)
            s += " " + numbers.get(o).toString();
            assertEquals(AcademyExercises.highAndLow(s), Collections.max(numbers) + " " + Collections.min(numbers));
        }
    }


    @Test
    public void testPowersOfTwo() {
      assertArrayEquals(new long[]{1}, AcademyExercises.powersOfTwo(0));
      assertArrayEquals(new long[]{1,2}, AcademyExercises.powersOfTwo(1));
      assertArrayEquals(new long[]{1,2,4,8,16}, AcademyExercises.powersOfTwo(4));
    }

    @Test
    public void testMaxMirror(){
        assertEquals(3, AcademyExercises.maxMirror(new int[]{1, 2, 3, 8, 9, 3, 2, 1}));
        assertEquals(3, AcademyExercises.maxMirror(new int[]{1, 2, 1, 4}));
        assertEquals(2, AcademyExercises.maxMirror(new int[]{7, 1, 2, 9, 7, 2, 1}));
        assertEquals(1, AcademyExercises.maxMirror(new int[]{1}));
        assertEquals(0, AcademyExercises.maxMirror(new int[]{}));
        assertEquals(1, AcademyExercises.maxMirror(new int[]{7, 1, 2, 9, 7, 2, 5}));
    }

    @Test
    public void testSumNumbers(){
        assertEquals(44, AcademyExercises.sumNumbers("aa11b33"));
        assertEquals(123, AcademyExercises.sumNumbers("abc123xyz"));
        assertEquals(18, AcademyExercises.sumNumbers("7 11"));
        assertEquals(7, AcademyExercises.sumNumbers("5$$1;;1!!"));
        assertEquals(0, AcademyExercises.sumNumbers(""));
        assertEquals(0, AcademyExercises.sumNumbers("Chocolate"));
    }


    @Test
    public void testFix34() {
        int[] test = new int[]{1, 3, 4, 1};
        test.equals(AcademyExercises.fix34(new int[]{1, 3, 1, 4}));
        test = new int[]{1, 3, 4, 1, 1, 3, 4};
        test.equals(AcademyExercises.fix34(new int[]{1, 3, 1, 4, 4, 3, 1}));
        test = new int[]{3, 4, 2, 2};
        test.equals(AcademyExercises.fix34(new int[]{3, 2, 2, 4}));
        test = new int[]{1, 3, 4, 1, 1, 3, 4};
        test.equals(AcademyExercises.fix34(new int[]{1, 3, 1, 4, 4, 3, 1}));
    }

    @Test
    public void testSwap() {
        TwoVariables tv = new TwoVariables(47, 53);
		tv.swap();
		assertEquals(53, tv.getA());
		assertEquals(47, tv.getB());
    }

    @Test
    public void testPost4() {
        int[] test = new int[]{1, 2};
        test.equals(AcademyExercises.post4(new int[]{2, 4, 1, 2}));
        test = new int[]{2};
        test.equals(AcademyExercises.post4(new int[]{4, 1, 4, 2}));
        test = new int[]{1, 2, 3};
        test.equals(AcademyExercises.post4(new int[]{4, 4, 1, 2, 3}));
    }

    @Test
    public void testcenteredAverage(){
        assertEquals(3, AcademyExercises.centeredAverage(new int[]{1, 2, 3, 4, 100}));
        assertEquals(5, AcademyExercises.centeredAverage(new int[]{1, 1, 5, 5, 10, 8, 7}));
        assertEquals(-3, AcademyExercises.centeredAverage(new int[]{-10, -4, -2, -4, -2, 0}));
        assertEquals(1, AcademyExercises.centeredAverage(new int[]{1, 1, 100}));
    }

    @Test
    public void testOnly14() {
        assertTrue(AcademyExercises.only14(new int[]{1, 4, 1, 4}));
        assertFalse(AcademyExercises.only14(new int[]{1, 4, 2, 4}));
        assertTrue(AcademyExercises.only14(new int[]{1, 1}));
    }

    @Test
    public void testSameStarChar() {
        assertTrue(AcademyExercises.sameStarChar("xy*yzz"));
        assertFalse(AcademyExercises.sameStarChar("xy*zzz"));
        assertTrue(AcademyExercises.sameStarChar("*xa*az"));
        assertTrue(AcademyExercises.sameStarChar(""));
        assertTrue(AcademyExercises.sameStarChar("**"));
    }

    @Test
    public void testXYBalance() {
        assertTrue(AcademyExercises.xyBalance("aaxbby"));
        assertFalse(AcademyExercises.xyBalance("aaxbb"));
        assertFalse(AcademyExercises.xyBalance("yaaxbb"));
    }

    @Test
    public void testMakeBricks() {
        assertTrue(AcademyExercises.makeBricks(3, 1, 8));
        assertFalse(AcademyExercises.makeBricks(3, 1, 9));
        assertTrue(AcademyExercises.makeBricks(3, 2, 10));
    }

    @Test
    public void testAlphabet() {
        char ch;
        int i=0;

        for (ch='a'; ch<='z'; ch++){
            assertEquals(i, AcademyExercises.alphabet().indexOf(ch));
            i++;
        }
        
    }
    
    @Test
    public  void testHasBad() {
        assertTrue(AcademyExercises.hasBad("xbad"));
        assertFalse(AcademyExercises.hasBad("bbbdsjj"));
        assertFalse(AcademyExercises.hasBad("xxxbad"));
    }

    @Test
    public void testLastDigit() {
        assertTrue(AcademyExercises.lastDigit(1,11,34));
        assertFalse(AcademyExercises.lastDigit(14,15,16));
        assertTrue(AcademyExercises.lastDigit(45,105,65));
    }

    @Test
    public void testMessage() {
        String expected = "Hello World";
        String result = AcademyExercises.message();
        assertEquals(expected, result);
    }

    @Test
    public void testTeaParty() {
        assertEquals(0, AcademyExercises.teaParty(5,3));
        assertEquals(1, AcademyExercises.teaParty(5,7));
        assertEquals(2, AcademyExercises.teaParty(5,10));
    }

    @Test
    public void testAlarmClock() {
        assertEquals("7:00", AcademyExercises.alarmClock(1, false));
        assertEquals("10:00", AcademyExercises.alarmClock(0, false));
        assertEquals("10:00", AcademyExercises.alarmClock(1, true));
        assertEquals("off", AcademyExercises.alarmClock(6, true));
    }

    @Test
    public void testCheckFineSettimana() {
        assertFalse(AcademyExercises.checkFineSettimana(2));
        assertTrue(AcademyExercises.checkFineSettimana(6));
    }
}