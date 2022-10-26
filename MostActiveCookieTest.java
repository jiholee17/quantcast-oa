import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class MostActiveCookieTest {

  MostActiveCookie mostActiveCookie;

  @Before
  public void setUp() {
    mostActiveCookie = new MostActiveCookie();
  }

  // map for date 2018-12-09
  private static HashMap<String, Integer> map1() {
    HashMap<String, Integer> map = new HashMap<>();
    map.put("AtY0laUfhglK3lC7", 2);
    map.put("SAZuXPGUrfbcn5UA", 1);
    map.put("5UAVanZf6UtGyKVS", 1);

    return map;
  }

  // map for date 2018-12-08
  private static HashMap<String, Integer> map2() {
    HashMap<String, Integer> map = new HashMap<>();
    map.put("SAZuXPGUrfbcn5UA", 1);
    map.put("4sMM2LxV07bPJzwf", 1);
    map.put("fbcn5UAVanZf6UtG", 1);

    return map;
  }

  // map for date not in CSV
  private static HashMap<String, Integer> map3() {
    HashMap<String, Integer> map = new HashMap<>();

    return map;
  }

  // most active cookies for 2018-12-09
  private static HashSet<String> active1() {
    HashSet<String> active = new HashSet<>();
    active.add("AtY0laUfhglK3lC7");

    return active;
  }

  // most active cookies for 2018-12-08
  private static HashSet<String> active2() {
    HashSet<String> active = new HashSet<>();
    active.add("SAZuXPGUrfbcn5UA");
    active.add("4sMM2LxV07bPJzwf");
    active.add("fbcn5UAVanZf6UtG");

    return active;
  }

  // most active cookies for date not in CSV
  private static HashSet<String> active3() {
    HashSet<String> active = new HashSet<>();

    return active;
  }

  @Test
  public void testGetDate() {
    System.out.println("Testing getDate...");

    String[] tokens1 = { "AtY0laUfhglK3lC7", "2018-12-09T14:19:00+00:00" };
    String[] tokens2 = { "4sMM2LxV07bPJzwf", "2018-12-08T21:30:00+00:00" };

    assertEquals("2018-12-09", mostActiveCookie.getDate(tokens1));
    assertEquals("2018-12-08", mostActiveCookie.getDate(tokens2));
  }

  @Test
  public void testCsvToMap() {
    System.out.println("Testing csvToMap...");

    HashMap<String, Integer> map1 = map1();
    HashMap<String, Integer> map2 = map2();
    HashMap<String, Integer> map3 = map3();

    assertEquals(map1, mostActiveCookie.csvToMap("cookie_log.csv", "2018-12-09"));
    assertEquals(map2, mostActiveCookie.csvToMap("cookie_log.csv", "2018-12-08"));
    assertEquals(map3, mostActiveCookie.csvToMap("cookie_log.csv", "2018-01-02"));
  }

  @Test
  public void testPrintMostActive() {
    System.out.println("Testing printMostActive...");

    HashMap<String, Integer> map1 = map1();
    HashMap<String, Integer> map2 = map2();
    HashMap<String, Integer> map3 = map3();
    HashSet<String> active1 = active1();
    HashSet<String> active2 = active2();
    HashSet<String> active3 = active3();

    System.out.println("Most active cookies for 2018-12-09:");
    assertEquals(active1, mostActiveCookie.printMostActive(map1));

    System.out.println("Most active cookies for 2018-12-08:");
    assertEquals(active2, mostActiveCookie.printMostActive(map2));

    assertEquals(active3, mostActiveCookie.printMostActive(map3));
  }
}