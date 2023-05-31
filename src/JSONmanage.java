import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
public class JSONmanage {
    private String path;
    private JSONObject jsonObject;
    private JSONArray jsonArrayPuntuacion;
    
    public void readFile(){
        try {
            FileReader fileReader = new FileReader(path);
            JSONParser jsonParser = new JSONParser();
            this.jsonObject = (JSONObject) jsonParser.parse(fileReader);
            this.jsonArrayPuntuacion = (JSONArray) jsonObject.get("puntuacions");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public JSONmanage(String jsonPath) {
        this.path = jsonPath;
        readFile();
        
    }

    public int getIdRegisterHighScoreUser(String name){
        int idRegisterHighScore = -1;
        int highScore = 0;
        for (int i = 0; i < jsonArrayPuntuacion.size(); i++) {
            JSONObject registresUsers = (JSONObject) jsonArrayPuntuacion.get(i);
            if (registresUsers.get("nom").equals(name)){
                if (Integer.parseInt(registresUsers.get("puntuacio").toString()) > highScore){
                    highScore = Integer.parseInt(registresUsers.get("puntuacio").toString());
                    idRegisterHighScore = i;
                }
            }
        }
        return idRegisterHighScore;
    }

    public String getParameterWithRegisterId(int id, String parameter){
        JSONObject registresUsers = (JSONObject) jsonArrayPuntuacion.get(id);
        return registresUsers.get(parameter).toString();
    }

    private int getLastIdRegister(){
        return Integer.parseInt( ( (JSONObject) jsonArrayPuntuacion.get(jsonArrayPuntuacion.size()-1) ).get("id").toString() );
    }

	@SuppressWarnings("unchecked")
    public int newRegister(String name, int score){
        JSONObject newRegister = new JSONObject();
        //set date in format yyyy/mm/dd
        String date = java.time.LocalDate.now().toString();
        //set hour in format hh:mm:ss
        String hour = java.time.LocalTime.now().toString();

        newRegister.put("nom", name);
        newRegister.put("puntuacio", score);
        newRegister.put("id", getLastIdRegister()+1);
        newRegister.put("data", date);
        newRegister.put("hora", hour);
        jsonArrayPuntuacion.add(newRegister);
        saveUpdateJson();
        return getLastIdRegister();


    }

    private void saveUpdateJson(){
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
