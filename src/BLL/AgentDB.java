package BLL;

import DLL.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class AgentDB {

    //Gets list of agents
    public static List<Agent> getAgents(){
        List<Agent> agents = null;

        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String selectQuery = "select AgentId, AgtFirstName, AgtMiddleInitial, AgtLastName, AgtBusPhone," +
                                    "AgtEmail, AgtPosition, AgencyId from Agents";

            //makes a sql statement
            Statement query = connect.createStatement();

            //assigns & executes statement
            ResultSet rs = query.executeQuery(selectQuery);

            //runs while reader has data
            while(rs.next()){
                Agent agent = new Agent(rs.getInt("AgentId"),
                                        rs.getString("AgtFirstName"),
                                        rs.getString("AgtMiddleInitial"),
                                        rs.getString("AgtLastName"),
                                        rs.getString("AgtBusPhone"),
                                        rs.getString("AgtEmail"),
                                        rs.getString("AgtPosition"),
                                        rs.getInt("AgencyId"));
                agents.add(agent);
            }

            }catch(Exception e) { e.printStackTrace(); }

        return agents;
    }
}
