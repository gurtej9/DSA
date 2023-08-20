public int prims(List<List<Pair>> g, int start, boolean[] vis)
    {
        mstEdges = new ArrayList<>();
        int mstWeight = 0;
        PriorityQueue<Pair> q=new PriorityQueue<>();
        q.add(new Pair(start, -1, -1, 0));

        while(!q.isEmpty())
        {
            int sz=q.size();
            while(sz-->0)
            {
                Pair rem=q.remove();
                int u = rem.u, v=rem.v, wt=rem.wt;
                if(vis[u])
                    continue;
                vis[u]=true;
                if(v!=-1)
                    mstEdges.add(rem);
                mstWeight+=wt;

                List<Pair> nbrs=g.get(u);
                for(int i=0;i<nbrs.size();++i)
                {
                    int child=nbrs.get(i).v;
                    q.add(new Pair(child, u, nbrs.get(i).idx, nbrs.get(i).wt));
                }
            }
        }
        return mstWeight;
    }
