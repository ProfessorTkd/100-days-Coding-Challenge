// Solution 1: naive depth first search (TLE)

// Time complexity: O(3 ^ N)

                class Solution {
                    public int tallestBillboard(int[] rods) {
                        int[] result = new int[1];
                        dfs(rods, 0, 0, 0, rods.length, result);
                        return result[0];
                    }
                    private void dfs(int[] rods, int left, int right, int level, int n, int[] result) {
                        if (level == n) {
                            if (left == right) {
                                result[0] = Math.max(left, result[0]);
                            }
                            return;
                        }
                        
                        dfs(rods, left, right, level + 1, n, result);
                        dfs(rods, left + rods[level], right, level + 1, n, result);
                        dfs(rods, left, right + rods[level], level + 1, n, result);
                    }
                }


// Solution 2: optimized depth first search

// Time complexity: O(3 ^ (N / 2))

                class Solution {
                    public int tallestBillboard(int[] rods) {
                        int n = rods.length;
                        Map<Integer, Integer> left = new HashMap<>();
                        Map<Integer, Integer> right = new HashMap<>();
                        dfs(rods, 0, 0, 0, n / 2, left);
                        dfs(rods, 0, 0, n / 2, n, right);
                        
                        int result = 0;
                        for (Map.Entry<Integer, Integer> lEntry : left.entrySet()) {
                            for (Map.Entry<Integer, Integer> rEntry : right.entrySet()) {
                                if (lEntry.getKey() + rEntry.getKey() == 0) {
                                    result = Math.max(lEntry.getValue() + rEntry.getValue(), result);
                                }
                            }
                        }
                        return result;
                    }
                
                    private void dfs(int[] rods, int l, int r, int level, int n, Map<Integer, Integer> map) {
                        if (level == n) {
                            Integer cur = map.get(l - r);
                            if (cur == null) {
                                map.put(l - r, l);
                            } else {
                                map.put(l - r, Math.max(cur, l));
                            }
                            return;
                        }
                        dfs(rods, l, r, level + 1, n, map);
                        dfs(rods, l + rods[level], r, level + 1, n, map);
                        dfs(rods, l, r + rods[level], level + 1, n, map);
                    }
                }


// Solution 3: pseudo polynomial dynamic programming

// Time complexity: O(N * sum)

// Data structure:
// n = the length of rods, sum = the cultimative sum of elements in the array;
// f[i][j] = the largest smaller height of two steel supports for previous i elements with the difference j;

// Induction rule:
// f[i][j] = maximum of 4 candidates

// f[i - 1][j], not using ith element;
// f[i - 1][j + rods[i - 1] + rods[i - 1], adding ith element to smaller one of two steel supports, and the smaller one is still shorter than the largers one;
// f[i - 1][rods[i - 1] - j] + rods[i - 1] - j, adding ith element to smaller one of two steel supports, and in this case, the smaller one becomes taller than the original larger one. Therefore, the value of f[i][j] should be equal to the larger one when f[i - 1][rods[i - 1] - j];
// f[i - 1][j - rods[i - 1]], adding ith element to larger one of two steel supports;
// We should pay attention that all the indexes must be valid(>= 0 && <= sum) and the values must be valid(!= -1).

// Base case:
// f[0][0] = 0.

                        class Solution {
                            public int tallestBillboard(int[] rods) {
                                int n = rods.length, sum = 0;
                                for (int rod : rods) sum += rod;
                                int[][] dp = new int[n + 1][sum + 1];
                                for (int[] arr : dp) Arrays.fill(arr, -1);
                                dp[0][0] = 0;
                                for (int i = 1; i <= n; i++) {
                                    for (int j = 0; j <= sum; j++) {
                                        dp[i][j] = dp[i - 1][j];
                                        if (j + rods[i - 1] <= sum && dp[i - 1][j + rods[i - 1]] != -1) {
                                            dp[i][j] = Math.max(dp[i][j], dp[i - 1][j + rods[i - 1]] + rods[i - 1]);
                                        } 
                                        if (dp[i - 1][Math.abs(j - rods[i - 1])] != -1) {
                                            dp[i][j] = Math.max(dp[i][j], dp[i - 1][Math.abs(j - rods[i - 1])] + Math.max(0, rods[i - 1] - j));
                                        }               
                                    }
                                }
                                return dp[n][0];
                            }
                        }    
