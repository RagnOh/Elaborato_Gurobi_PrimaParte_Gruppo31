\ Model pool
\ LP format - for model browsing. Use MPS format to capture full model detail.
Maximize
  4 x1 + 4 x2 + 6 x3 + 10 x4 + 7 x5 + 4 x6 - x7 + 7 x8
Subject To
 c0: 4 x1 - 3 x6 <= 10
 c1: 3 x5 + 5 x6 + 4 x8 <= 1
 c2: - 2 x2 - 3 x3 + 7 x4 - x5 + 4 x7 - 7 x8 >= -8
 c3: - 3 x8 <= 5
 c4: - 6 x1 + 7 x2 - 3 x8 <= 8
 c5: - 7 x1 + 5 x4 + 6 x7 <= 5
 c6: - 2 x3 + 7 x7 <= 5
Bounds
End
