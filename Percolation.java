import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
   private boolean [] open;
   private boolean [] full;
   
   private int nodeZeroId;
   private int nodeEndId;
   
   private int num;
   private int n;
   
   private int openCounter = 0;
   
   private WeightedQuickUnionUF wquuf, wquufbw;
    
   public Percolation(int n) {
       
       if (n <= 0) {
            throw new IllegalArgumentException();
        }
       
        this.n = n;
        this.num = n * n; 
               
        this.nodeZeroId = this.num;
        this.nodeEndId = this.num+1;
        
        this.open = new boolean[this.num+2];
        this.full = new boolean[this.num+2];
        
        for(int i = 0; i < this.num; i++ ) {
            
            this.open[i] = false;
            this.full[i] = false;
        }
        
        this.open[this.nodeZeroId] = true;
        this.open[this.nodeEndId] = true;
        
        this.full[this.nodeZeroId] = false;
        this.full[this.nodeEndId] = false;
        
        this.wquuf = new WeightedQuickUnionUF(this.num+2);
        this.wquufbw = new WeightedQuickUnionUF(this.num+2);

   }
   
   private int toIndex(int i, int j) {
        return (i - 1) * n + j - 1;
    }
   
   private int[] getAllNeighbors(int row, int col) {
        
        int[] neighbors = new int[4];
        
        for (int i = 0; i < 4; i++) neighbors[i] = -1;
        
        if (row > 1) {
            int nPreviousLine = toIndex(row-1, col);
            neighbors[0] = nPreviousLine;
        } else {
            neighbors[0] = this.nodeZeroId;
        }
        if (row < this.n) {
            int nNextLine = toIndex(row+1, col);
            neighbors[1] = nNextLine;           
        } else {
             neighbors[1] = this.nodeEndId;
        }
        if (col > 1) {
            int nPreviousColumn = toIndex(row, col -1);
             neighbors[2] = nPreviousColumn;
        }
        if (col < this.n) {
            int nNextColumn = toIndex(row, col + 1);
            neighbors[3] = nNextColumn;
        }
        return neighbors;
    }
    
    private void asseguraLimites(int p) {
        if (p < 1 || p > n) {
            throw new IndexOutOfBoundsException();
        }
    }
   
   
   // create n-by-n grid, with all sites blocked
   public void open(int row, int col) {
       
        this.asseguraLimites(row);
        this.asseguraLimites(col);
       
        int index = this.toIndex(row, col);
        if (!isOpen(row, col)) {
            this.open[index] = true;
            this.openCounter++;    
            
            int[] neighbors = this.getAllNeighbors(row, col);
        
            //System.out.println("index "+ index);
            
            for (int neighbor:neighbors) {
                
                //System.out.println(neighbor);
                
                if (neighbor >= 0 && this.isOpen(neighbor)) {
                    
                    wquuf.union(index, neighbor);
                    if (neighbor <= this.num) {add
                        wquufbw.union(index, neighbor);
                    }
                    
                }
                
            }
        }
   }    // open site (row, col) if it is not open already
   
   
   private boolean isOpen(int index) {
       
       return this.open[index];
   } 
   
   public boolean isOpen(int row, int col) {
       this.asseguraLimites(row);
       this.asseguraLimites(col);
       return this.open[this.toIndex(row, col)];
       
   } // is site (row, col) open?
   public boolean isFull(int row, int col) {
       
       this.asseguraLimites(row);
       this.asseguraLimites(col);
       return wquufbw.connected(nodeZeroId, this.toIndex(row, col));

   } // is site (row, col) full?
   
   
   public int numberOfOpenSites()  {
       return this.openCounter;
   }     // number of open sites
   
   
   public boolean percolates() {
       
       return wquuf.connected(nodeZeroId, nodeEndId);
   }             // does the system percolate?

   public static void main(String[] args) {
       
       Percolation p = new Percolation(4);
       p.open(1,1);
       p.open(2,1);
       p.open(2,1);
       p.open(3,1);
       p.open(4,1);
       
       System.out.println(p.numberOfOpenSites());
       
       System.out.println(p.percolates());
        
        
   }  // test client (optional)
}