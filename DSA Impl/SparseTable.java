class SparseTable
{
    int MAX=18;
    int[][] table;
    int[] bin_log;
 
    SparseTable(int[] arr)
    {
        int n=arr.length;
        table=new int[n][MAX];
        bin_log=new int[n+1];
 
        for(int i=2;i<=n;++i)
        {
            bin_log[i]=bin_log[i/2]+1;
        }
        for(int i=0;i<n;++i)
            table[i][0]=arr[i];
 
        for(int j=1;j<MAX;++j)
        {
            for(int i=0;i+(1<<j)-1<n;++i)
            {
                table[i][j]=Math.min(table[i][j-1], table[i+(1<<(j-1))][j-1]);
            }
        }
 
    }
    public int query(int l, int r)
    {
        int len=r-l+1;
        int k=bin_log[len];
        return Math.min(table[l][k], table[r-(1<<k)+1][k]);
    }
}