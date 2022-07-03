class MultiSet<T> 
{
    private int size;
    TreeMap<T, Integer> map;

    public MultiSet(){
            size = 0;
            map = new TreeMap<>();
        }
        public int size(){return size;}
        public int dsize(){return map.size();}
        public boolean isEmpty(){return size==0;}
        public void add(T t){
            size++;
            map.put(t, map.getOrDefault(t, 0)+1);
        }
        public boolean remove(T t){ // single removal of occurence of element
            if(!map.containsKey(t))return false;
            size--;
            int c = map.get(t);
            if(c==1)map.remove(t);
            else map.put(t, c-1);
            return true;
        }
        public int freq(T t){return map.getOrDefault(t, 0);}
        public boolean contains(T t){return map.getOrDefault(t,0)>0;}
        public T ceiling(T t){return map.ceilingKey(t);}
        public T floor(T t){return map.floorKey(t);}
        public T first(){return map.firstKey();}
        public T last(){return map.lastKey();}
        
        public ArrayList<T> keySet()
        {
            ArrayList<T> list=new ArrayList<>();

            for(T key: map.keySet())
            {
                int cnt=map.get(key);
                while(cnt-->0)
                {
                    list.add(key);
                }
            }
            return list;
        }

        public String toString()
        {
            StringBuilder sb=new StringBuilder();
            for(T key: map.keySet())
            {
                int cnt=map.get(key);
                while(cnt-->0)
                {
                    sb.append(key+" ");
                }
            }
            return sb.toString();
        }
}