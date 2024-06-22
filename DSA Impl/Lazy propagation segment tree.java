class SegmentTree {

    int[] segmentTree;
    int[] lazy;
    int n;

    public SegmentTree(int[] arr) {

        n = arr.length;
        segmentTree = new int[4 * arr.length];
        lazy = new int[4 * arr.length];
        for (int i = 0; i < segmentTree.length; i++) {
            segmentTree[i] = 0;
        }
        build(arr, 0, arr.length - 1, 0);
    }
    
    public void updateSegmentTreeRangeLazy(int startRange, int endRange, int delta) {
        updateSegmentTreeRangeLazy(startRange, endRange, delta, 0,
            n - 1, 0);
    }

    public int rangeMinimumQueryLazy(int qlow, int qhigh) {
        return rangeMinimumQueryLazy(qlow, qhigh, 0, n - 1, 0);
    }

    private void build(int[] input, int low, int high, int pos) {
        if (low == high) {
            segmentTree[pos] = input[low];
            return;
        }
        int mid = (low + high) / 2;
        build(input, low, mid, 2 * pos + 1);
        build(input, mid + 1, high, 2 * pos + 2);
        segmentTree[pos] = Math.min(segmentTree[2 * pos + 1], segmentTree[2 * pos + 2]);
    }

    private void updateSegmentTreeRangeLazy(int startRange, int endRange,
        int delta, int low, int high, int pos) {
        if (low > high) {
            return;
        }

        //make sure all propagation is done at pos. If not update tree
        //at pos and mark its children for lazy propagation.
        if (lazy[pos] != 0) {
            segmentTree[pos] += lazy[pos];
            if (low != high) { //not a leaf node
                lazy[2 * pos + 1] += lazy[pos];
                lazy[2 * pos + 2] += lazy[pos];
            }
            lazy[pos] = 0;
        }

        //no overlap condition
        if (startRange > high || endRange < low) {
            return;
        }

        //total overlap condition
        if (startRange <= low && endRange >= high) {
            segmentTree[pos] += delta;
            if (low != high) {
                lazy[2 * pos + 1] += delta;
                lazy[2 * pos + 2] += delta;
            }
            return;
        }

        //otherwise partial overlap so look both left and right.
        int mid = (low + high) / 2;
        updateSegmentTreeRangeLazy(startRange, endRange,
            delta, low, mid, 2 * pos + 1);
        updateSegmentTreeRangeLazy(startRange, endRange,
            delta, mid + 1, high, 2 * pos + 2);
        segmentTree[pos] = Math.min(segmentTree[2 * pos + 1], segmentTree[2 * pos + 2]);
    }

    private int rangeMinimumQueryLazy(int qlow, int qhigh,
        int low, int high, int pos) {

        if (low > high) {
            return Integer.MAX_VALUE;
        }

        //make sure all propagation is done at pos. If not update tree
        //at pos and mark its children for lazy propagation.
        if (lazy[pos] != 0) {
            segmentTree[pos] += lazy[pos];
            if (low != high) { //not a leaf node
                lazy[2 * pos + 1] += lazy[pos];
                lazy[2 * pos + 2] += lazy[pos];
            }
            lazy[pos] = 0;
        }

        //no overlap
        if (qlow > high || qhigh < low) {
            return Integer.MAX_VALUE;
        }

        //total overlap
        if (qlow <= low && qhigh >= high) {
            return segmentTree[pos];
        }

        //partial overlap
        int mid = (low + high) / 2;
        return Math.min(rangeMinimumQueryLazy(qlow, qhigh,
                low, mid, 2 * pos + 1),
            rangeMinimumQueryLazy(qlow, qhigh,
                mid + 1, high, 2 * pos + 2));
    }
}
