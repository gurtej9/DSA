class Tree
{
    int[][] up;
    int MAX=17;
    Tree(int[] parent)
    {
        int n=parent.length;
        up=new int[n][MAX];
        
        for(int i=0;i<n;++i)
            up[i][0]=parent[i];

        for(int j=1;j<MAX;++j)
            for(int i=0;i<n;++i)
                up[i][j]=up[up[i][j-1]][j-1];
        
    }
    public int kthParent(int u, int k)
    {
        for(int i=0;i<MAX;++i)
        {
            if(((1<<i)&k) > 0)
            {
                u=up[u][i];
            }
        }
        return u;
    }
    public void display()
    {
        for(int i=0;i<up.length;++i)
        {
            for(int j=0;j<17;++j)
            {
                System.out.print(up[i][j]+" ");
            }
            System.out.println();
        }
    }
}