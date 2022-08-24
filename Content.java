
public class Content
{
    public int id;
    public String value;
    public Content(int id, String value)
    {
        this.id=id;
        this.value=value;
    }

    public void print() {
        System.out.println(this.id + " " + this.value);
    }
}
