import java.util.Vector;

public enum SeasonEnum {
    SPRING("春天"),SUMMER("夏天"),AUMUTN("秋天"),WINTER("冬天");

    private String chinese;

    SeasonEnum(String chinese) {
        this.chinese = chinese;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    private Vector<String> names;
}

