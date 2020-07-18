package com.wht.demo.leetCode.offer;

/**
 * desc.
 *
 * @author wht
 */
public class NumIslands {
    char[][] grid;

    //垂直方向
    int width;
    //水平方向
    int length;

    //岛屿数量
    int count = 0;

    //int searchCount = 0;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        this.grid = grid;
        this.length = grid[0].length;
        this.width = grid.length;
        //search(0, 0);

        for (int i = 0, gridLength = grid.length; i < gridLength; i++) {
            for (int j = 0, charsLength = grid[i].length; j < charsLength; j++) {
                if (grid[i][j] == '1'){
                    count++;
                    mark(i,j);
                }
            }
        }

        return count;
    }

    public void mark(int x, int y) {
        //if (x < 0 || x >= width || y < 0 || y >= length) {
        //    return;
        //}
        //
        //if (grid[x][y] != '1') {
        //    return;
        //}
        //searchCount++;
        //grid[x][y] = '0';
        //
        //mark(x - 1, y);
        //mark(x, y - 1);
        //mark(x + 1, y);
        //mark(x, y + 1);
        if (x < 0 || x >= width || y < 0 || y >= length) {
            return;
        }

        if (grid[x][y] != '1') {
            return;
        }
        //searchCount++;
        grid[x][y] = '0';

        mark(x - 1, y);
        mark(x, y - 1);
        mark(x + 1, y);
        mark(x, y + 1);
    }

    //public void search(int x, int y) {
    //    if (x < 0 || x >= width || y < 0 || y >= length) {
    //        return;
    //    }
    //    //已经探索过的位置值替换为'#'，以免重复探索
    //    if (grid[x][y] == '#') {
    //        return;
    //    }
    //
    //    if (grid[x][y] == '1') {
    //        //grid[x][y] == 1，遇到了岛屿,将当前岛屿进行标记
    //        count++;
    //        mark(x, y);
    //    }
    //    searchCount++;
    //    //向四周探索
    //    //其实只需要搜索右方和下方
    //
    //    //这时当前结点有两种情况：1.未探索过；2.已被标记过的岛屿，这种情况下有大概率周围的已经扫描过了，直接找到下一个还没扫描的开始扫描
    //    //if (grid[x][y] == '0'){
    //    //    search(x + 1, y);
    //    //    search(x, y + 1);
    //    //} else {
    //    //    //直接找下一个未扫描的位置
    //    //    while (grid[++x][y] !=)
    //    //
    //    //    search(x + 1, y);
    //    //    search(x, y + 1);
    //    //}
    //
    //    search(x + 1, y);
    //    search(x, y + 1);
    //
    //}


}
