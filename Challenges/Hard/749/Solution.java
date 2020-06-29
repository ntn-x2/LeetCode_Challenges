class Solution {
    
    class Result {
        Set<String> moreCriticalArea = new HashSet<>();
        Set<String> infectableCells = new HashSet<>();
    }
    
    public int containVirus(int[][] grid) {
        int gridElements = grid.length * grid[0].length;
        
        Result mostCriticalZone = null;
        int totalWalls = 0;
        do {
            mostCriticalZone = null;
            Set<String> infectedCells = new HashSet<>();
            boolean[][] visitedCells = new boolean[grid.length][grid[0].length];
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if (grid[row][col] != 1 || visitedCells[row][col]) {
                        continue;
                    }
                    Result currentCriticalZone = getCriticalZone(row, col, grid, visitedCells);
                    // We only retain the most critical zone, and from all the others we take the infectable cells
                    if (mostCriticalZone == null || mostCriticalZone.infectableCells.size() < currentCriticalZone.infectableCells.size()) {
                        if (mostCriticalZone != null) {
                            infectedCells.addAll(mostCriticalZone.infectableCells);
                        }
                        mostCriticalZone = currentCriticalZone;
                    } else {
                        infectedCells.addAll(currentCriticalZone.infectableCells);
                    }
                }
            }
            if (mostCriticalZone != null) {
                totalWalls += buildWalls(mostCriticalZone.moreCriticalArea, grid);
                expandVirus(infectedCells, grid);
            }
        } while (mostCriticalZone != null && mostCriticalZone.moreCriticalArea.size() < gridElements);
        return totalWalls;
    }
    
    private Result getCriticalZone(int row, int col, int[][] grid, boolean[][] visitedCells) {
        Result resultContainer = new Result();
        getCriticalZone(row, col, resultContainer, grid, visitedCells);
        return resultContainer;
    }
    
    private void getCriticalZone(int row, int col, Result result, int[][] grid, boolean[][] visitedCells) {
        if (row < 0 || row >= grid.length) {
            return;
        }
        
        if (col < 0 || col >= grid[0].length) {
            return;
        }
        
        if (grid[row][col] != 1) {
            return;
        }        

        if (visitedCells[row][col]) {
            return;
        }

        visitedCells[row][col] = true;
        
        String currentLocation = String.format("%d-%d", row, col);
        if (!result.moreCriticalArea.add(currentLocation)) {
            return;
        }
        
        if (row-1 >= 0 && grid[row-1][col] == 0) {
            result.infectableCells.add(String.format("%d-%d", row-1, col));
        }
        if (row+1 < grid.length && grid[row+1][col] == 0) {
            result.infectableCells.add(String.format("%d-%d", row+1, col));
        }
        if (col-1 >= 0 && grid[row][col-1] == 0) {
            result.infectableCells.add(String.format("%d-%d", row, col-1));
        }
        if (col+1 < grid[0].length && grid[row][col+1] == 0) {
            result.infectableCells.add(String.format("%d-%d", row, col+1));
        }
        
        getCriticalZone(row-1, col, result, grid, visitedCells);
        getCriticalZone(row+1, col, result, grid, visitedCells);
        getCriticalZone(row, col-1, result, grid, visitedCells);
        getCriticalZone(row, col+1, result, grid, visitedCells);
    }
    
    private int buildWalls(Set<String> areaToClose, int[][] grid) {
        for (String indexEntry: areaToClose) {
            String[] splittedValues = indexEntry.split("-");
            int row = Integer.parseInt(splittedValues[0]);
            int col = Integer.parseInt(splittedValues[1]);
            grid[row][col] = -1;
        }
        int wallsBuilt = 0;
        for (String indexEntry: areaToClose) {
            String[] splittedValues = indexEntry.split("-");
            int row = Integer.parseInt(splittedValues[0]);
            int col = Integer.parseInt(splittedValues[1]);
            if (row-1 >= 0 && grid[row-1][col] != -1) {
                wallsBuilt += 1;
            }
            if (row+1 < grid.length && grid[row+1][col] != -1) {
                wallsBuilt += 1;
            }
            if (col-1 >= 0 && grid[row][col-1] != -1) {
                wallsBuilt += 1;
            }
            if (col+1 < grid[0].length && grid[row][col+1] != -1) {
                wallsBuilt += 1;
            }
        }
        return wallsBuilt;
    }
    
    private void expandVirus(Set<String> infectedAreas, int[][] grid) {
        for (String indexEntry: infectedAreas) {
            String[] splittedValues = indexEntry.split("-");
            int row = Integer.parseInt(splittedValues[0]);
            int col = Integer.parseInt(splittedValues[1]);
            grid[row][col] = 1;
        }
    }
}