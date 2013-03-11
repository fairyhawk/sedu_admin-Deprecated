package exp.vo;

import java.io.Serializable;

public class TestSsiVO implements Serializable{
    private int id;
    private String name;
    private static final long serialVersionUID = -6504437205389820048L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "TestSsiVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestSsiVO testSsiVO = (TestSsiVO) o;

        if (id != testSsiVO.id) return false;
        if (name != null ? !name.equals(testSsiVO.name) : testSsiVO.name != null) return false;

        return true;
    }

    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }


}
