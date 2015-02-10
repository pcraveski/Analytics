package wc_multithread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilesFinder {
    public final ArrayList<String> liste;

	public FilesFinder() {
		ArrayList<String> a=new ArrayList<>();
                this.liste=a;
	}
	/**
         * 
         *
         * @param directoryPath
         * Permet d'ajouter le contenu d'un dossier dans un FilesFinder
         */
	public void findFiles(String directoryPath) {
            List<String> resultat=null;
		File directory = new File(directoryPath);
		if(!directory.exists()){
			System.out.println("Le fichier/répertoire '"+directoryPath+"' n'existe pas");
		}else if(!directory.isDirectory()){
			System.out.println("Le chemin '"+directoryPath+"' correspond à un fichier et non à un répertoire");
		}else{
			File[] subfiles = directory.listFiles();
			String message = "Le répertoire '"+directoryPath+"' contient "+ subfiles.length+" fichier"+(subfiles.length>1?"s":"");
			System.out.println(message);
			for(int i=0 ; i<subfiles.length; i++){
				this.liste.add(directoryPath+"/"+subfiles[i].getName());
			}
		}
                
	}
        /**
         * Permet d'afficher le contenu d'un FilesFinder
         */
        public void read(){
            if (this.liste.size()==0){System.out.println("Le dossier est vide");}
            else{
                for (int i=0;i<this.liste.size();i++){
                    System.out.println(this.liste.get(i));
                }
            }
        }
}