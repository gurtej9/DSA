class Trie{

    private class TrieNode{
        TrieNode[] children;
        boolean isEnd;

        TrieNode(){
            children=new TrieNode[26];
            isEnd=false;
        }
    }
    TrieNode root;
    Trie(){
        root=new TrieNode();
    }
    
    public void display(){
        TrieNode curr=root;
        String str="";
        ArrayList<String> words=new ArrayList<>();
        for(int i=0;i<26;++i)
        {
            if(curr.children[i]!=null)
                dfs(curr.children[i], str+(char)(i+97), words);
        }

        System.out.println(words);
    }
    public void dfs(TrieNode curr, String str, ArrayList<String> words){

        if(curr.isEnd==true)
            words.add(str);
        for(int i=0;i<26;++i)
        {
            if(curr.children[i]!=null)
                dfs(curr.children[i], str+(char)(i+97), words);
        }
    }
    public boolean search(String word){
        TrieNode curr=root;

        for(int i=0;i<word.length();++i)
        {
            char c=word.charAt(i);

            int idx=c-'a';
            if(curr.children[idx]==null)
                return false;
            else
            {
                curr=curr.children[idx];
            }
        }

        return curr.isEnd;
    }
    public void insert(String word){
        TrieNode curr=root;

        for(int i=0;i<word.length();++i)
        {
            char c=word.charAt(i);

            int idx=c-'a';
            if(curr.children[idx]==null)
            {
                TrieNode nn=new TrieNode();
                curr.children[idx]=nn;
                curr=nn;
            }
            else
            {
                curr=curr.children[idx];
            }
        }
        curr.isEnd=true;
    }
    // assuming no duplicates exist
    public boolean delete(String word) {

        if(!search(word))
            return false;
        TrieNode curr = root;
        TrieNode startRemovalNode = null;
        TrieNode parentOfStartRemovalNode = null, parentOfCurr = null;
        for(int i=0;i<word.length();++i)
        {
            char c=word.charAt(i);

            int idx=c-'a';
            boolean isOtherLetterRelatedHere = false;
            for(int j=0;j<26;++j)
            {
                if(j==idx) continue;
                if(curr.children[j] != null)
                    isOtherLetterRelatedHere = true;
            }
            if(curr.isEnd)
                isOtherLetterRelatedHere = true;


            if(i==0 || isOtherLetterRelatedHere)
                startRemovalNode = null;
            else if(startRemovalNode == null)
            {
                parentOfStartRemovalNode = parentOfCurr;
                startRemovalNode = curr;
            }

            System.out.println(startRemovalNode);

            parentOfCurr = curr;
            curr=curr.children[idx];
            
        }
        for(int i=0;i<26;++i)
        {
            if(curr.children[i] != null)
                startRemovalNode = null;
        }        
        // delete the path
        if(startRemovalNode == null)
        {
            curr.isEnd = false;
        }
        else
        {
            for(int i=0;i<26;++i)
            {
                if(parentOfStartRemovalNode.children[i] == startRemovalNode)
                {
                    parentOfStartRemovalNode.children[i] = null;
                    break;
                }
            }
        }
        

        return true;
    }
}
