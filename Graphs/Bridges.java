class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        
        ans=new ArrayList<>();
        
        ArrayList<ArrayList<Integer>> g=new ArrayList<>();
        
        for(int i=0;i<n;++i)
        {
            g.add(new ArrayList<>());
        }
        for(int i=0;i<connections.size();++i)
        {
            g.get(connections.get(i).get(0)).add(connections.get(i).get(1));
            g.get(connections.get(i).get(1)).add(connections.get(i).get(0));
        }
        
        // dp[u] ->  number of back-edges passing over the edge between u and its parent. 
        dp=new int[n]; 
        level=new int[n];
        bridges=0;
        Arrays.fill(level, -1);
        
        
        dfsTree(g, 0, -1);
        return ans;
    }
    static List<List<Integer>> ans;
    static int[] dp;
    static int[] level;
    static int bridges=0;
    public void dfsTree(ArrayList<ArrayList<Integer>> g, int u, int p)
    {
        ArrayList<Integer> nbrs=g.get(u);
        
        for(int i=0;i<nbrs.size();++i)
        {
            int child=nbrs.get(i);
            if(level[child]==-1) // unvisited node
            {
                level[child]=level[u]+1;
                dfsTree(g, child, u);
                dp[u]+=dp[child];
            }
            else if(level[child] < level[u]) //edge upwards
            {
                dp[u]++;
            }
            else if(level[child] > level[u]) // edge downwards
            {
                dp[u]--;
            }
        }
        
        dp[u]--;
        
        if (dp[u] == 0) {
            ArrayList<Integer> bridge=new ArrayList();
            bridge.add(p);
            bridge.add(u);
            ans.add(bridge);
            bridges++;
        }
    }
}