class Heap{

	//MIN HEAP

	ArrayList<Integer> heap;
	int size;
	Heap()
	{
		heap=new ArrayList<>();
		size=0;
	}
	private int leftChildIdx(int i)
	{
		return 2*i+1;
	}
	private int rightChildIdx(int i)
	{
		return 2*i+2;
	}
	private int getParent(int i)
	{
		return (i-1)/2;
	}
	public void add(int ele)
	{
		heap.add(ele);
		size++;

		upheapify(size-1);
	}
	public int poll()
	{
		if(size==0)
		{
			return Integer.MIN_VALUE;
		}
		int removed=heap.get(0);
		heap.set(0, heap.get(size-1));
		heap.remove(size-1);
		size--;
		downheapify(0);
		return removed;
	}

	private void upheapify(int idx)
	{
		int parentIdx=getParent(idx);
		while(idx>0 && heap.get(parentIdx)>heap.get(idx))
		{
			swap(parentIdx, idx);
			upheapify(parentIdx);
		}
	}
	private void downheapify(int idx)
	{
		int smallestIdx=idx;
		int leftIdx=leftChildIdx(idx);
		int rightIdx=rightChildIdx(idx);
		if(leftIdx<size && heap.get(leftIdx)<heap.get(idx))
		{
			smallestIdx=leftIdx;
		}
		if(rightIdx<size && heap.get(rightIdx)<heap.get(smallestIdx))
		{
			smallestIdx=rightIdx;
		}
		if(smallestIdx!=idx)
		{
			swap(idx, smallestIdx);
			downheapify(smallestIdx);
		}
	}
	private void swap(int i, int j)
	{
		int t=heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, t);
	}
	public void display()
	{
		System.out.println(heap);
	}
	public boolean isEmpty()
	{
	    return size==0;
	}

}