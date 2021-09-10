class Tree
{
    int[][] up;
    int[] level;
    ArrayList<ArrayList<Integer>> adj;
    int MAX=17;
    Tree(int[] parent)
    {
        int n=parent.length;
        adj=new ArrayList<>();
        buildTree(parent);
        up=new int[n][MAX];
        level=new int[n];

        dfs(0, -1, 0);
        
        for(int i=0;i<n;++i)
            up[i][0]=parent[i];

        for(int j=1;j<MAX;++j)
            for(int i=0;i<n;++i)
                up[i][j]=up[up[i][j-1]][j-1];
        
    }
    public void dfs(int u, int p, int lvl)
    {
        if(u!=0)
            level[u]=lvl;
        ArrayList<Integer> nbrs=adj.get(u);
        for(int i=0;i<nbrs.size();++i)
        {
            int child=nbrs.get(i);
            if(child!=p)
            {
                dfs(child, u, lvl+1);
            }
        }
    }
    public void buildTree(int[] parent)
    {
        for(int i=0;i<parent.length;++i)
            adj.add(new ArrayList<>());
        for(int i=0;i<parent.length;++i)
        {
            adj.get(i).add(parent[i]);
            adj.get(parent[i]).add(i);
        }
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

        for(int i=0;i<up.length;++i)
        {
            System.out.print(level[i]+" ");
        }
    }
    public int lca(int u, int v)
    {
        if(level[u]>level[v])
        {
            int t=u;
            u=v;
            v=t;
        }
        //level[u] < level[v]

        int diff=level[v]-level[u];
        for(int i=MAX-1;i>=0;i--)
        {
            if((diff & (1<<i))>0)
                v=up[v][i];
        }
        if(u==v)
            return u;

        for(int i=MAX-1;i>=0;i--)
        {
            int uParent=up[u][i];
            int vParent=up[v][i];

            if(uParent!=vParent)
            {
                u=uParent;
                v=vParent;
            }
        }
        u=up[u][0];

        return u;
    }
}