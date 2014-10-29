package be.condorcet;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

	 private Button bt;
	 private EditText ed1;
	 private EditText ed2;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			
			
			ed1=(EditText) findViewById(R.id.texte1);
			ed2=(EditText) findViewById(R.id.texte2);
		
			bt=(Button) findViewById(R.id.bouton1);
			bt.setOnClickListener(
				new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
					  ed2.setText("recherche en cours");
					  MyAccesDB adb=new MyAccesDB(MainActivity.this);
					  adb.execute();
						
					}
				}
			 );	
		    		
				
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
		
		
		
		
 class MyAccesDB extends AsyncTask<String,Integer,Boolean> {
    private String resultat;
						
			public MyAccesDB(MainActivity pActivity) {
			
				link(pActivity);
				// TODO Auto-generated constructor stub
			}

			private void link(MainActivity pActivity) {
				// TODO Auto-generated method stub
			
				
			}

			@Override
			protected Boolean doInBackground(String... arg0) {
				
			
				Connection dbConnect=null;

		        String userid="ora1";
		        String password="oracle";

		        String server = "mons-oracle.condorcet.be";
		        String port="1521";
		        String database="xe";
		        try{
		        	
		          Class.forName ("oracle.jdbc.OracleDriver");
		          
		          String url = "jdbc:oracle:thin:@//"+server+":"+port+"/"+database;
		         dbConnect = DriverManager.getConnection(url, userid, password);
		        
		          Statement stmt=dbConnect.createStatement();;
		          String nomRech=ed1.getText().toString();
		       
		          ResultSet rs = stmt.executeQuery("select * from client2 where nom = '"+nomRech+"'");
		         
		         resultat="";
		          while (rs.next()) {
		        	  
		            String nom = rs.getString("NOM");

		            String prenom = rs.getString("PRENOM");
		       
		            int n = rs.getInt("NUMCLI");
		            resultat+=nom+" "+" "+prenom+" "+n+"\n";
		        
		            
		          }
		             
		         if(resultat.equals("")) resultat="nom introuvable"; 
		         
		         }
		          catch (SQLException e) {
		        	resultat="erreur SQL :"+e.getMessage();
		        }
		          catch (Exception e) {
		        	resultat="erreur indéterminée :"+e.getMessage();
		          }
		         try {
		          dbConnect.close();
		          }
		          catch (Exception e) { 
		          }
		         
		         
			
				return true;
			}
			
			protected void onPostExecute(Boolean result){
				 super.onPostExecute(result);
				  ed2.setText(resultat);
							
			}
	
		}
			
}
