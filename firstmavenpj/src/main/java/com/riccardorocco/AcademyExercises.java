package com.riccardorocco;
import java.math.BigInteger;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class AcademyExercises {

    private static HashMap<Long, Long> pentaFibCache = new HashMap<>();
    static {
        pentaFibCache.put(0L, 0L);
        pentaFibCache.put(1L, 1L);
        pentaFibCache.put(2L, 1L);
        pentaFibCache.put(3L, 2L);
        pentaFibCache.put(4L, 4L);
    }

    public static void main(String[] args){
        System.out.println(message());

        System.out.println("Venerdì lavorativo ho la sveglia alle: " + alarmClock(4, false));
        System.out.println("Venerdì in vacanza ho la sveglia alle: " + alarmClock(4, true));

		TwoVariables tv = new TwoVariables(3, 20);
        System.out.println("\nPrima dello swap:\na: " + tv.getA() + "\nb: " + tv.getB() + "\n");
		tv.swap();
		System.out.println("\nDopo lo swap:\na: " + tv.getA() + "\nb: " + tv.getB() + "\n");

        System.out.println("\n" + alphabet() + "\n");

        System.out.println(AcademyExercises.pentaFib(10) + ": " + pentaFib(9) + " " + pentaFib(8) 
                            + " " + pentaFib(7) + " " + pentaFib(6) + " " + pentaFib(5) + " " + pentaFib(4) 
                            + " " + pentaFib(3) + " " + pentaFib(2) + " " + pentaFib(1) + " " + pentaFib(0));
    }

    /**
     * Sum the values of two Strings and return a String with that sum. The numbers are BIG!
     * Please consider not using java.math
     * @param a
     * @param b
     * @return
     */
    public static String addBigNumbers(String a, String b) {
        return new BigInteger(a).add(new BigInteger(b)).toString();
    }
    public static String addBigNumbersII(String a, String b) {
        StringBuilder r = new StringBuilder();
        int carry = 0;
        int idx = 0;
        while (carry != 0 || idx < a.length() || idx < b.length()) {
            int x = idx < a.length() ? Character.getNumericValue(a.charAt(a.length() + ~idx)) : 0;
            int y = idx < b.length() ? Character.getNumericValue(b.charAt(b.length() + ~idx)) : 0;
            int m = x + y + carry;
            r.append(m % 10);
            carry = m / 10;
            ++idx;
        }
        return r.reverse().toString().replaceAll("^0+", "");
    }

    /**
     * 
     * @param phrase A string
     * @return A string where all the first letters are capitalized
     *         EX: "hello world" -> "Hello World"
     */
    public String toJadenCase(String phrase) {
        if (phrase == null || phrase.isEmpty()) return null;
        
        return Arrays.stream(phrase.split(" "))
                     .map(s -> s.substring(0, 1).toUpperCase().concat(s.substring(1, s.length())))
                     .collect(Collectors.joining(" "));
    }
    public String toJadenCaseII(String phrase) {
        if ( phrase==null || phrase.isEmpty() ) return null;
        String singleWords[] = phrase.split("\\s"), result = "";  
        for(String sw : singleWords){  
            String firstChar = sw.substring(0,1);  
            String otherChars = sw.substring(1);  
            result += firstChar.toUpperCase() + otherChars + " ";  
        }  
        return result.trim(); 
    }

    /**
     * The White House has a wall-penetrating radar security system that sees everything.
     * Process the radar image.
     * Legend:
     *         # walls
     *         X POTUS
     *         o elves
     * @param house
     * @return  true if POTUS really is home alone.
     */
    public static boolean allAlone(char[][] house) {
        for (int i = 0; i < house.length; i++){
            for (int j = 0; j < house[0].length; j++){
                if (house[i][j] == 'X'){
                    if (lookForElf(house, j, i)) {return false;}
                }
            }  
        }         
        return true;
    }
    public static boolean lookForElf(char[][] house, int j, int i) {
        try {
            //cerco un elfo
            if (house[i][j] == 'o'){ return true; }
            //non cerco dove ho già cercato o oltre il muro
            if (house[i][j] == '#' || house[i][j] == '-') { return false; }
            //segnaposto
            house[i][j] = '-';
            //cerco elfo a dx o sx
            if (lookForElf(house, j + 1, i) || lookForElf(house, j - 1, i)) { return true; }
            //cerco elfo up o dw
            if (lookForElf(house, j, i + 1) || lookForElf(house, j, i - 1)) { return true; }
        } catch (ArrayIndexOutOfBoundsException e) { return false; }
        return false;
    }

    /** IS MY FRIEND CHEATING? [from CodeWars]
     *   A friend of mine takes the sequence of all numbers from 1 to n (where n > 0).
     *   Within that sequence, he chooses two numbers, a and b.
     *   He says that the product of a and b should be equal to the sum of all numbers in the sequence, excluding a and b.
     *   Given a number n, could you tell me the numbers he excluded from the sequence?
     * @param n
     * @return An array or a string with all (a, b) which are the possible removed numbers in the sequence 1 to n.
     */
    public static List<long[]> removNb(long n) {
        List<long[]> list = new ArrayList<long[]>();
        long sumN = n * (n+1) / 2;
        for (long i=1; i<=n+1; i++){
            /*
             *  i*j = 1 + ... + n - i - j 
             *  j*(i+1) = 1 + ... + n -i
             *  j = (1 + ... + n - i) / ( i + 1 )
             */
            long j = (sumN -1) / (i+1);
            if (j<=n && i*j == sumN - (i+j)){
                list.add(new long[] {i, j});
            }
        }
        return list;
    }
    public static List<long[]> removNbII(long n) {
        long sumN = n * (n+1) / 2;
        return LongStream.rangeClosed(1, n)
                        .filter( i -> ( sumN + 1 ) / ( i + 1 ) == 0 )
                        .mapToObj( i -> new long[] { i, ( sumN + 1 ) / ( i + 1 ) - 1})
                        .filter( i -> i[0] != i[1] && i[1] <= n )
                        .collect( Collectors.toList() );
    }

    /**
     * We want to create a simple interpreter of assembler which will support the following instructions
     *      mov x y - copies y (either a constant value or the content of a register) into register x
     *      inc x - increases the content of the register x by one
     *      dec x - decreases the content of the register x by one
     *      jnz x y - jumps to an instruction y (y can be a register or a constant) steps away 
     *                (positive means forward, negative means backward). But only if x (a constant or a register) is not zero.
     * 
     * @param program Input list with the sequence of the program instructions
     * @return A dictionary (a table in COBOL) with the contents of the registers
     */
    public static Map<String, Integer> interpret(String[] program){
        Map<String, Integer> out = new HashMap<String, Integer>();
        for (int instructionIndex=0; instructionIndex<program.length; instructionIndex++){
            String[] instruction = program[instructionIndex].split(" ");
            if (instruction[0].equals("mov")) { 
                if (out.containsKey(instruction[2])){
                    out.put(instruction[1], out.get(instruction[2]));
                } else {
                    out.put(instruction[1], Integer.parseInt(instruction[2]));}
                }
            if (instruction[0].equals("inc")) { out.put(instruction[1], out.get(instruction[1]) + 1);}
            if (instruction[0].equals("dec")) { out.put(instruction[1], out.get(instruction[1]) - 1);}
            if (instruction[0].equals("jnz")){
                if ((out.containsKey(instruction[1]) && out.get(instruction[1])!=0) || (!out.containsKey(instruction[1]) && Integer.parseInt(instruction[1])!=0)){
                    if (out.containsKey(instruction[2])){ instructionIndex += out.get(instruction[2]);} 
                    else { instructionIndex += Integer.parseInt(instruction[2]) - 1;}
                }
            }
        }
        return out;
    }

    /**
     * 
     * @param myTime My local time
     * @param myZone My timezone
     * @param friendZone my friend's time zone
     * @return The time at my friend's time zone based on my time and timezone
     * @throws ParseException
     */
    public static String getFriendDateAndTime(String myTime, String myZone, String friendZone) throws ParseException {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); 
            LocalDateTime myDateTime = LocalDateTime.parse(myTime, format);
            long diff = ChronoUnit.SECONDS
                            .between(myDateTime.atZone(ZoneId.of(friendZone)),myDateTime.atZone(ZoneId.of(myZone)));
            LocalDateTime myfriendDateTime = LocalDateTime.parse(myTime, format).plusSeconds(diff);
            return format.format(myfriendDateTime).toString();

            /* ALTERNATIVE PF
             * return LocalDateTime.parse(myTime,format)
             *                     .atZone(ZoneId.of(myZone))
             *                     .withZoneSameInstant(ZoneId.of(friendZone))
             *                     .format(format);
             */
	}
    public static String getFriendDateAndTimeII(String myTime, String string, String friendZone) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); 
        LocalDateTime dateTime = LocalDateTime.parse(myTime, formatter);
        
        ZoneId friendIdZone = ZoneId.of(friendZone);
        ZoneId myIdZone = ZoneId.of(string);
        
        ZonedDateTime zdtme = dateTime.atZone(myIdZone); 
        ZonedDateTime zdtfriend = zdtme.withZoneSameInstant(friendIdZone);
        return zdtfriend.format(formatter);
    }
    
    /**
     *              Your task is to sort a given string. Each word in the string will contain a single number. 
     *              This number is the position the word should have in the result (from 1 to 9).
     * @param words
     * @return      If the input string is empty, return an empty string.
     */
    public static String order(String words) {
        if (words.equals("")) return words;

        return Arrays.stream(words.split(" "))
                    .sorted((s1, s2) -> s1.replaceAll("\\D", "").compareTo(s2.replaceAll("\\D", "")))
                    .collect(Collectors.joining(" "));
    }

    /**
     * 
     * @param strings An array of strings
     * @return A Map<String, Integer> with a key for each different string,
     *         with the value the number of times that string appears in the array.
     */
    public Map<String, Integer> wordCount(String[] strings) {
        Map<String, Integer> result = new HashMap<>();
        for (String s: strings){
            result.put(s, Collections.frequency(Arrays.asList(strings), s));
        }
        return result;
    }
    /**
     * 
     * @param csvFileContent String containing the chars of a csv file  
     * @return The same csv file but with colums sorted alphabetically based in their first element
     */
    public static String sortCsvColumns(String csvFileContent){
        ArrayList<ArrayList<String>> colums = new ArrayList<>();
        HashMap<String, ArrayList<String>> csv = new HashMap<>(); 
        String line=""; Boolean firstLine=true;

        for (int i=0; i<csvFileContent.length(); i++){
            if (i==csvFileContent.length()-1) {
                line+=csvFileContent.charAt(i);
            }
            if (csvFileContent.charAt(i)!='\n' && i!=csvFileContent.length()-1){
                line+=csvFileContent.charAt(i);
            } else {
                String[] splittedLine = line.split(";");
                for (int k=0; k<splittedLine.length; k++){
                    if (firstLine) {
                        colums.add(new ArrayList<String>(Arrays.asList(splittedLine[k])));
                    }
                    else {
                        colums.get(k).add(splittedLine[k]);
                    }
                }
                firstLine=false;
                line="";
            }
        }

        for(ArrayList<String> s : colums){
            csv.put(s.get(0), s);
        }

        List<String> mapByKey = new ArrayList<>(csv.keySet());
        Collections.sort(mapByKey, String.CASE_INSENSITIVE_ORDER);

        String result="";
        int k=0;
        while (k<csv.get(mapByKey.get(0)).size()){
            for (String s: mapByKey){
                if (mapByKey.indexOf(s)==mapByKey.size()-1){
                    result+=csv.get(s).get(k)+"\n";
                } else {
                    result+=csv.get(s).get(k)+";";
                }
            }
            k++;
        }

        return result.trim();
    }
    public static String sortCsvColumnsII(String csvFileContent){
        String[][] content = Arrays.stream(csvFileContent.split("\\n")).map(s -> s.split(";")).toArray(String[][]::new);
        String[][] inverted = new String[content[0].length][content.length];
        for(int i = 0; i < content.length; i++) {
            for(int j = 0; j < content[i].length; j++) {
                inverted[j][i] = content[i][j];
            }
            }
        Arrays.sort(inverted, (a1, a2) -> a1[0].toLowerCase().compareTo(a2[0].toLowerCase()));
        for(int i = 0; i < inverted.length; i++) {
            for(int j = 0; j < inverted[i].length; j++) {
                content[j][i] = inverted[i][j];
            }
            }
        return Arrays.stream(content).map(line -> String.join(";", line)).collect(Collectors.joining("\n"));
    }


    /**
     * 
     * @param words A list of strings
     * @return The first strings in words where the next one starts with the same last letter of it's previous
     */
    public static List<String> theGame(List<String> words){
        List<String> validWords = new ArrayList<>();
        if (words.size()==0 || words.get(0)=="") { return validWords; }
        else {
            String previous = "";
            boolean first = true;
            for (String current : words){
                if (first) { 
                    previous=current; 
                    validWords.add(current);
                    first=false;
                } else {
                    if (current!="" && previous.charAt(previous.length()-1) == current.charAt(0)){
                        validWords.add(current);
                        previous=current;
                    } else {  break; }
                }
            }
        }
        return validWords;
    }

    /**
     * 
     * @param n Paramether of the PentaBonacci sequence
     * @return The amount of odd numbers in the f(n) sequence
     */
    public static long countOddPentaFibList(long n){
        ArrayList<Long> fib = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            fib.add(pentaFibCached(i));
        }
        return fib.stream().filter(p -> p%2!=0).distinct().count();
    }
    public static long countOddPentaFibArray(long n) {
        long[] fib = new long[(int) (n+1)];
        for (int i=0; i<n; i++) {
            fib[i] = pentaFib(i);
        }
        return Arrays.stream(fib).filter(p -> p%2!=0).distinct().count();
    }
    private static long pentaFib(long n) {
        if (n == 0) { return 0; }
        else if (n == 1 || n == 2) { return 1; }
        else if (n == 3) { return 2; }
        else if (n == 4) { return 4; }
        else {
            return pentaFib(n-1) + pentaFib(n-2) + pentaFib(n-3) + pentaFib(n-4) + pentaFib(n-5);
        }
    }
    private static long pentaFibCached(long n){
        Long l = pentaFibCache.get(n);
        if (l != null) {
            return l;
        } else {
            l = pentaFibCached(n - 1) + pentaFibCached(n - 2) + pentaFibCached(n - 3) + pentaFibCached(n - 4) + pentaFibCached(n - 5);
            pentaFibCache.put(n, l);
            return l;
        }
    }

    /**
     * 
     * @param numbers An array of 10 integers (between 0 and 9).
     * @return A string of those numbers in the form of a phone number.
     */
    public static String createPhoneNumber(int[] n) {
        return "(" + n[0]+n[1]+n[2] + ") " + n[3]+n[4]+n[5] + "-" + n[6]+n[7]+n[8]+n[9];
        //return "(%d%d%d) %d%d%d-%d%d%d%d".formatted(Arrays.stream(numbers).boxed().toArray(Integer[]::new));
    }

    /**
     *                                                                               1   2   3   4
     * @param N Integer N                                                            12  13  14  5
     * @return An array N*N with numbers from 1 to N*N in a clockwise spiral         11  16  15  6
     *         EX: [N=4] Output: [[1,2,3,4],[12,13,14,5],[11,16,15,6],[10,9,8,7]]    10  9   8   7
     */
    public static int[][] createSpiral(int N) {
        int[][] spiral = new int[N][N];
        int rows=N, cols=N, up=0, dx=cols-1, dw=rows-1, sx=0;
        int dir = 1, cont=1;
 
        while (up <= dw && sx <= dx && cont<(N*N)+1) {
      
            if (dir == 1) { //sx -> dx
                for (int i = sx; i <= dx; ++i) {
                    spiral[up][i]=cont;
                    cont++;
                }
                ++up; //mi sposto verso il basso
                dir = 2;
            } 
            else if (dir == 2) { //up -> dw
                for (int i = up; i <= dw; ++i) {
                    spiral[i][dx]=cont;
                    cont++;
                }
                --dx; //mi sposto verso sinistra
                dir = 3;
            } 
            else if (dir == 3) { //dx -> sx
                for (int i = dx; i >= sx; --i) {
                    spiral[dw][i]=cont;
                    cont++;
                }
                --dw; //mi sposto in su
                dir = 4;
            } 
            else if (dir == 4) { //dw -> up
                for (int i = dw; i >= up; --i) {
                    spiral[i][sx]=cont;
                    cont++;
                }
                ++sx; //mi sposto a destra
                dir = 1;
            }
        }
        
        return spiral;
    }

    /*
    You live in the city of Cartesia where all roads are laid out in a perfect grid. 
    You arrived ten minutes too early to an appointment, so you decided to take the opportunity to go for a short walk. 
    The city provides its citizens with a Walk Generating App on their phones -- everytime you press the button it sends you 
    an array of one-letter strings representing directions to walk (eg. ['n', 's', 'w', 'e']). 
    You always walk only a single block for each letter (direction) and you know it takes you one minute to traverse 
    one city block. */
    /**
     * 
     * @param walk An array of one-letter strings representing directions to walk (eg. ['n', 's', 'w', 'e'])
     * @return true if the walk the app gives you will take you exactly ten minutes (you don't want to be early or late!) 
     *         and will, of course, return you to your starting point. Return false otherwise.
     */
    public static boolean tenMinWalk(char[] walk) {
        int ns=0, ew=0;
        for (int i=0; i<walk.length; i++){
            if (walk[i]=='n') ns+=1;
            if (walk[i]=='s') ns-=1;
            if (walk[i]=='e') ew+=1;
            if (walk[i]=='w') ew-=1;
        }
        return (ns==0 && ew==0) && walk.length==10;
        //PAQUALE'S ALTERNATIVE
        //return isValid(walk);
    }
    public static boolean isValid(char[] walk) {
        Map<Character, Long> result = IntStream.range(0, walk.length)
                                        .mapToObj(i -> walk[i])
                                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (walk.length != 10) return false;
        else return result.get('n') == result.get('s') && result.get('w') == result.get('e');
    }

    /**
     * 
     * @param numbers Unsorted array of integers
     * @return The largest pair sum in the sequence. EX: [10, 14, 2, 23, 19] -->  42 (= 23 + 19)
     */
    public static int largestPairSum(int[] numbers){
        int biggest = Math.max(numbers[0], numbers[1]); 
        int bigger = Math.min(numbers[0], numbers[1]);
        for (int i=2; i<numbers.length; i++){
            if (numbers[i]>biggest){bigger=biggest; biggest=numbers[i];}
            else if (numbers[i]>bigger) {bigger=numbers[i];}
        }
        
        return biggest+bigger;

        // ALTERNATIVE
        // Arrays.sort(numbers);
        // return numbers[numbers.length-1] + numbers[numbers.length-2];
    }

    /**
     * 
     * @param text The input string can be assumed to contain only alphabets (both uppercase and lowercase) and numeric digits.
     * @return The count of distinct case-insensitive alphabetic characters and numeric digits that occur more than once 
     *         in the input string.
     */
    public static int duplicateCount(String text) {
        int count=0;
        text=text.toLowerCase();

        for (int i=0; i<text.length()-1; i++){
            if (text.charAt(i)=='x') continue;
            for (int j=i+1; j<text.length(); j++){
                if (text.charAt(i)==text.charAt(j) && text.charAt(i)!='*') {
                    count++; text=text.replace(text.charAt(i), '*'); break;
                }
            }
        }

        return count;
    }
    public static int duplicateCount2(String text) {
        HashMap<String,Integer> occurrences = new HashMap<String,Integer>();
        for(String value: text.toLowerCase().split("")){
          if(occurrences.containsKey(value))
            occurrences.put(value, occurrences.get(value)+1);
          else 
            occurrences.put(value, 1);
        }
        int count = 0;
        for(String key: occurrences.keySet()){
          if (occurrences.get(key) > 1) count++;
        }
        return count;
    }

    /**
     * 
     * @param card The cost of the movie membership card (500€)
     * @param ticket The cost of the movie ticket (15€) [SystemA]
     * @param perc The %cost of the ticket if you have a membership card (90%) [SystemB]
     * @return  How many times he must go to the cinema so that the final result of System B, 
     *          when rounded up to the next dollar, will be cheaper than System A.
     */
    public static int movie(int card, int ticket, double perc) {
        double systemA=0, systemB=card; int n=0;
        while ( Math.ceil(systemB) >= systemA ) {
            systemA += ticket;
            systemB += ticket * Math.pow(perc,++n);
        }
        return n;
    }

    /**
     * 
     * @param numbers String of space separated numbers
     * @return The highest and lowest number
     */
    public static String highAndLow(String numbers) {
        int max=Integer.MIN_VALUE, min=Integer.MAX_VALUE;
        String[] separated = numbers.split(" ");

        if (numbers.length()==1) { return numbers + " " + numbers; }
        else {
            for (int i=0; i<separated.length; i++) {
                max = Math.max(max, Integer.parseInt(separated[i]));
                min = Math.min(min, Integer.parseInt(separated[i]));
            }
        }
        
        return(max + " " + min);
        
        //LAMBDA FUNCTIONS
        //int[] res = Arrays.asList(separated).stream().mapToInt(Integer::parseInt).sorted().toArray();
        //return res[res.length - 1] + " " + res[0];     
    }

    /**
     * 
     * @param n An integer
     * @return  Takes a non-negative integer n as input, and returns a list of all the powers of 2 
     *          with the exponent ranging from 0 to n ( inclusive ).
     */
    public static long[] powersOfTwo(int n){
        long[] powers = new long[n+1];
        for (int i=0; i<n+1; i++){
          powers[i] = (long) Math.pow(2, i);
        }
        return powers;
    }

    /**
     * We'll say that a "mirror" section in an array is a group of contiguous elements such that somewhere in the array, 
     * the same group appears in reverse order. The largest mirror in {1, 2, 3, 8, 9, 3, 2, 1} is length 3 (the {1, 2, 3} part).
     * @param nums
     * @return  Return the size of the largest mirror section found in the given array.
     *          EX: maxMirror([1, 2, 3, 8, 9, 3, 2, 1]) → 3
     *              maxMirror([1, 2, 1, 4]) → 3
     *              maxMirror([7, 1, 2, 9, 7, 2, 1]) → 2
     */
    public static int maxMirror(int[] nums) {
        int maxMirrorLength= 0;
        
        //Devo scorrere avanti e indietro contemporaneamente alla ricerca di elementi uguali
        for(int i = 0; i < nums.length; i++) {
            int mirrorLength = 0; //reset di mirrorLength. Mi serve per poter scorrere di count posizioni rispetto ad i
            for(int j = nums.length - 1; j >= 0 && i + mirrorLength < nums.length; j--) {
                if(nums[i + mirrorLength] == nums[j]) { //finchè scorro -> <- e sono uguali aumento la lunghezza del mirror
                    mirrorLength++;
                } else { //primo carattere diverso --> verifico se è più grande del maxMirrorLength precedente
                    maxMirrorLength = Math.max(maxMirrorLength, mirrorLength);
                    mirrorLength = 0; //e reset
                }
            }
            //devo verificare anche fuori dal jfor in caso di lenght=0 e palindrome                                                    
            maxMirrorLength = Math.max(maxMirrorLength, mirrorLength);
        }
    
        return maxMirrorLength;
    }
    
    /**
     * 
     * @param str A string
     * @return The sum of the numbers appearing in the string, ignoring all other characters. 
     *         A number is a series of 1 or more digit chars in a row. 
     *         EX: sumNumbers("aa11b33") → 44
     *         (Note: Character.isDigit(char) tests if a char is one of the chars '0', '1', .. '9'. 
     *                Integer.parseInt(string) converts a string to an int.)
     */
    public static int sumNumbers(String str) {
        String tmp = "0"; Integer sum=0;

        for (int i=0; i<str.length(); i++){
            if (Character.isDigit(str.charAt(i))){
                tmp += str.charAt(i);
            } else {
                sum += Integer.parseInt(tmp);
                tmp = "0";
            }
        }
        sum += Integer.parseInt(tmp);
        return sum;
    }

    /**
     * 
     * @param nums
     * @return An array that contains exactly the same numbers as the given array, 
     *         but rearranged so that every 3 is immediately followed by a 4. 
     *         Do not move the 3's, but every other number may move. 
     *         The array contains the same number of 3's and 4's, every 3 has a number after it that is not a 3, 
     *         and a 3 appears in the array before any 4.
     *         fix34([1, 3, 1, 4]) → [1, 3, 4, 1]
     *         fix34([1, 3, 1, 4, 4, 3, 1]) → [1, 3, 4, 1, 1, 3, 4]
     *         fix34([3, 2, 2, 4]) → [3, 4, 2, 2]
     */
    public static int[] fix34(int[] nums) {
        int last4 = 0;
        for (int i = 0; i < nums.length-1; i++) { //Scorro l'array
            if (nums[i] == 3) { //Se trovo un 3
                int j = last4+1; //Parto dall'ultimo 4 trovato/scambiato
                while ( nums[j]!=4 && !(j==nums.length-1)){ //Scorro l'array Per trovare un 4
                    j++;
                }
                nums[j] = nums[i+1]; //Scambio col 4
                nums[i+1] = 4; //Metto il 4 dopo il 3
                last4 = j; //Aggiorno la posizione dell'ultimo 4 trovato
            }
        }
        return nums;
    }
    

    /**
     * 
     * @param nums Given a non-empty array of ints
     * @return A new array containing the elements from the original array that come after the last 4 in the original array. 
     *         The original array will contain at least one 4. EX: post4([2, 4, 1, 2]) → [1, 2]
     *         Note that it is valid in java to create an array of length 0.
     */
    public static int[] post4(int[] nums) {
        int lastIndexOf = 0;
        
        for (int i=0; i<nums.length; i++){
            if (nums[i]==4) lastIndexOf=i;
        }

        int[] post4 = new int[nums.length - lastIndexOf - 1];

        for (int i=0; i<post4.length; i++ ){
            post4[i]=nums[i+lastIndexOf+1];
        }

        return post4;
    }

    /**
     * 
     * @param nums An array of integers
     * @return Return the "centered" average of an array of ints, which we'll say is the mean average of the values, 
     *         except ignoring the largest and smallest values in the array. 
     *         If there are multiple copies of the smallest value, ignore just one copy, and likewise for the largest value. 
     *         Use int division to produce the final average. You may assume that the array is length 3 or more.
     */
    public static int centeredAverage(int[] nums) {
        if (nums.length==0) return 0;
        int low=Integer.MAX_VALUE, high=Integer.MIN_VALUE, sum=0;
        for (int num : nums){
            low = Math.min(low, num);
            high = Math.max(high, num);
            sum += num;
        }

        return (sum-high-low)/(nums.length-2);
    }

    /**
     * 
     * @param nums An array of integers
     * @return TRUE if every element is a 1 or a 4
     */
    public static boolean only14(int[] nums) {
        for (int num : nums) {
            if ( !(num==1 || num==4) ) return false;
        }
        return true;
    }

    /**
     * 
     * @param str Input String
     * @return TRUE if for every '*' in the string, if there are chars both immediately before and after the star, they are the same.
     */
    public static boolean sameStarChar(String str) {

        //Ricorrenza carattere *
        //long count = str.chars().filter(ch -> ch == '*').count();
        int count = 0;
        for (int i=0; i<str.length(); i++){
            if (str.charAt(i)=='*') count++;
        }

        if (str.contains("*")) {
            if (count==1 && (str.indexOf("*")==0 || str.indexOf("*")==str.length()-1) || count==str.length()) { 
                return true;
            }
            else {
                boolean flag = false;
                for (int i=1; i<str.length()-1; i++){
                    if(str.charAt(i)=='*') {
                        flag = str.charAt(i-1)==str.charAt(i+1);
                    }
                }
                return flag;
            }
        }
        return true;
    }
    

    /**
     * 
     * @param str Input String
     * @return TRUE if there is an "x" but then there is an "y" in a higher place
     */
    public static boolean xyBalance(String str) {
        if(!str.contains("x") || (str.lastIndexOf("x")<str.lastIndexOf("y"))) { return true; }

        return false;
    }
    
    /**
     * 
     * @param small Amount of bricks of length 1
     * @param big Amount of bricks of length 5
     * @param goal How long the wall has to be
     * @return TRUE if the goal is reachable with the given bricks
     */
    public static boolean makeBricks(int small, int big, int goal) {
        // Mi salvo i mattoni delle unità
        int unit = goal % 10;
        // False ovvio: goal maggiore della lunghezza totale dei mattoni
        if (goal>((big*5)+small)) { return false; }
        /* Caso 1: le unità sono maggiori del big brick. Uso un big brick ma poi non ho abbastanza small brick 
           Caso 2: le unità sono minori del big brick ma non abbiamo abbastanza small brick */
        if (unit>5 && (unit-5)>small) { return false; }    
        if (unit<5 && small<unit) { return false; }
        return true;

        // return goal - Math.min(big, goal/5)*5 <= small;
    }

    /**
     * 
     * @return A string containing all the alphabet letters in lowercase and uppercase
     */
    public static String alphabet(){
        String alphabet = "";
        char ch;

        for( ch = 'a' ; ch <= 'z' ; ch++ ){
            if (ch == 'a') alphabet = "a";
            else alphabet = alphabet + ch;
          }
         
        for( ch = 'A' ; ch <= 'Z' ; ch++ ){
            alphabet = alphabet + ch;
        }

        return alphabet;
    }
        
    /**
     * 
     * @return Just a HelloWorld
     */
    public static String message(){
        return "Hello World";
    }
    
    /**
     * 
     * @param tea Rating given to the TEA at the party
     * @param candy Rating given to the CANDYs at the party
     * @return 0: both item have a rating lower than 5
     *         1: at least one of the two has a rating higher than 5
     *         2: one of the imes has a rating at least double than the other
     */
    public static int teaParty(int tea, int candy) {
        if (tea<5 || candy<5) { return 0; }
        else if ( tea>=2*candy || candy>=2*tea ) {return 2;}
        else { return 1; }
    }

    /**
     * 
     * @param a integer
     * @param b integer
     * @param c integer
     * @return TRUE if at least two of the param have the same last digit
     */
    public static boolean lastDigit(int a, int b, int c) {
        return (a % 10 == b % 10 || a % 10 == c % 10 || b % 10 == c % 10);
    }

    /**
     * 
     * @param str Input String
     * @return TRUE if str contains the word BAD starting in position 0 or 1
     */
    public static boolean hasBad(String str) {
        String bad= "bad";
        return(!(str.indexOf(bad)>1) && str.contains("bad"));
    }

        /**
     * 
     * @param giorno 0=dom, 1=lun, ... , 6=sab
     * @param vacanza boolean true or false
     * @return 7:00 (1-5 false), 10:00 (1-5 true || 6-0 false), off (0-6 true)
     */
    public static String alarmClock(int giorno, boolean vacanza){
        if( !checkFineSettimana(giorno) && !vacanza) {return "7:00";}
        else if (  (checkFineSettimana(giorno) && !vacanza) || (!checkFineSettimana(giorno) && vacanza) ) {return "10:00";}
        else {return "off";}
    }

    /**
     * 
     * @param giorno 0=dom, 1=lun, ... , 6=sab
     * @return true if 0 || 6
     */
    public static boolean checkFineSettimana(int giorno) {
        if (giorno == 0 || giorno == 6) { return true; }
        return false;
    }
    
}
