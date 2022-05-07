



import java.util.ArrayList;

public class multinode <T> {
    protected T data;
    protected int id=1;
    protected multinode parent=null;
    protected ArrayList<multinode> children;
    
    public multinode(T data,int id)
    {
        this.data = data;
        children = new ArrayList<multinode>();
        parent=null;
        this.id=id;
    }
    public multinode(T data,multinode p,int id)
    {
        this.data = data;
        children = new ArrayList<multinode>();
        parent=p;
        this.id=id;
    }
    public void setData(T data)
    {
        this.data = data;
    }
    public T getData()
    {
        return data;
    }
    public multinode getNode(int x)
    {
        if (x >= 0 && x < children.size()) {
            return children.get(x);

        } else {
            return null;

        }
    }
    public multinode getParent()
    {
        return parent;
    }
    public void insertchildren(T data,int id)
    {
        multinode n=new multinode(data,this,id);
        children.add(n);
    }
    boolean isEmpty()
    {
        return data==null;
    }
}

