import entity.Document;

public class Main {
    public static void main(String[] args) {
        Document doc = new Document();
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                doc.addCharacter(i,j, (char) ('a' + i + j), "arial", false, false,10);
            }
        }

        doc.display();
    }
}

