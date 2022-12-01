# TwitterSearchAlgorithm
Twitter search algorithm for MySQL database


SearchAlgorithmForTwitter.java uses a HashMap to store a sorted set of charsInARow.
Hashmaps map.get() method goes to the last declared entry called and returns
that value and we print that value. When topResult[] array is assigned values
it may assigned the same username to multiple positions, which we then print.
SearchAlgorithmForTwitter2.java seeks to allow us to use an AssertThat to access
different values of HashMap that happen to have the same key. We end up using 
two HashMaps. One Map<int, String> map2 = new HashMap<int, String>();
 Two Map<List<int>, String> map = new HashMap<List<int>, String>();
 A HashMap for saving the same value and a HashMap for saving the next set of values,
after the number of duplicates we want is put into our topResult[]
