class Solution
{
    //Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> articulationPoints(int n, ArrayList<ArrayList<Integer>> g)
    {
        
        dp=new int[n]; 
        level=new int[n];
        articulationPts=0;
        Arrays.fill(level, -1);
        ans=new ArrayList<>();
        level[0]=0;
        dfsTree(g, 0);
        Collections.sort(ans);
        if(articulationPts==0)
        {
            ans.add(-1);
        }
        return ans;
    }
    static ArrayList<Integer> ans;
    static int[] dp;
    static int[] level;
    static int articulationPts=0;
    public void dfsTree(ArrayList<ArrayList<Integer>> g, int u)
    {
        ArrayList<Integer> nbrs=g.get(u), childDPs=new ArrayList<>();
        int noOfChilds=0;
        int par=0;
        for(int i=0;i<nbrs.size();++i)
        {
            int child=nbrs.get(i);
            if(level[child]==-1) // unvisited node
            {
                noOfChilds++;
                level[child]=level[u]+1;
                if (!childDPs.isEmpty())
    			{
    				childDPs.set(childDPs.size() - 1, childDPs.get(childDPs.size() - 1) - par);
    				par = 0;
    			}
                dfsTree(g, child);
                dp[u]+=dp[child];
                childDPs.add(dp[child]);
                child++;
            }
            else if(level[child] < level[u]) //edge upwards
            {
                dp[u]++;
            }
            else if(level[child] > level[u]) // edge downwards
            {
                par++;
                dp[u]--;
            }
        }
        dp[u]--;
        
        // leaf is never a articulation point
        if(noOfChilds==0)
        {
            return;
        }
        
        // check for root (special case)
        if(u==0)
        {
            if(noOfChilds>1)
            {
                ans.add(0);
                articulationPts++;
            }
            return;
        }
            
        // general case (if any of the children doesnt have back edge, 
        // then u is an articulation point)
        childDPs.set(childDPs.size() - 1, childDPs.get(childDPs.size() - 1) - par);
        boolean is=false;
        for(int ele: childDPs)
        {
            if(ele==0)
            {
                is=true; 
                break;
            }
        }
        if(is)
        {
            ans.add(u);
            articulationPts++;
        }
    }
}