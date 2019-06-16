package database.entity;

import javax.persistence.*;

@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Log_seq")
    @SequenceGenerator(name="Log_seq", sequenceName="logid", allocationSize = 1)
    private int id;
    private Integer player;
    private Integer lvl;
    private Integer stamina;
    private String nameofstep;
    private String nameofaction;
    public  Log(){}
    public  Log(Integer player,Integer lvl,Integer stamina,String nameofstep,String nameofAction){
        this.player = player;
        this.lvl = lvl;
        this.stamina = stamina;
        this.nameofstep=nameofstep;
        this.nameofaction=nameofAction;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "player")
    public Integer getPlayer() {
        return player;
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }

    @Basic
    @Column(name = "lvl")
    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    @Basic
    @Column(name = "stamina")
    public Integer getStamina() {
        return stamina;
    }

    public void setStamina(Integer stamina) {
        this.stamina = stamina;
    }

    @Basic
    @Column(name = "nameofstep")
    public String getNameofstep() {
        return nameofstep;
    }

    public void setNameofstep(String nameofstep) {
        this.nameofstep = nameofstep;
    }

    @Basic
    @Column(name = "nameofaction")
    public String getNameofaction() {
        return nameofaction;
    }

    public void setNameofaction(String nameofaction) {
        this.nameofaction = nameofaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        if (id != log.id) return false;
        if (player != null ? !player.equals(log.player) : log.player != null) return false;
        if (lvl != null ? !lvl.equals(log.lvl) : log.lvl != null) return false;
        if (stamina != null ? !stamina.equals(log.stamina) : log.stamina != null) return false;
        if (nameofstep != null ? !nameofstep.equals(log.nameofstep) : log.nameofstep != null) return false;
        if (nameofaction != null ? !nameofaction.equals(log.nameofaction) : log.nameofaction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (lvl != null ? lvl.hashCode() : 0);
        result = 31 * result + (stamina != null ? stamina.hashCode() : 0);
        result = 31 * result + (nameofstep != null ? nameofstep.hashCode() : 0);
        result = 31 * result + (nameofaction != null ? nameofaction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Игрок: "+player+" уровень: "+lvl+" выносливость: "+" "+stamina+" "+nameofstep+nameofaction;
    }
}
