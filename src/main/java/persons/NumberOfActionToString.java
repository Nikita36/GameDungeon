package persons;

public enum NumberOfActionToString {
    down(1, "Спуск"),downhill(2,"Быстрый спуск"),relexation(3,"Отдых"),specialAction(4,"Специальное умение");
    private int numberOfAction;
    private String tittle;

     NumberOfActionToString(int numberOfAction, String tittle) {
        this.numberOfAction = numberOfAction;
        this.tittle = tittle;
    }
   public static NumberOfActionToString valueOf(int numberOfAction){
         switch (numberOfAction){
             case 1: return down;
             case 2: return down;
             case 3: return relexation;
             case 4:return specialAction;
         }
         return null;
    }
}

