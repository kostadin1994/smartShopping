package com.example.smartshopping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
/* This activity produces a list of radiobuttons containing different values 
 * when a button is chosen its value will be sent to the next activity 
 * where it will be added to the current balance of the user 
 */
public class TopUp extends Activity {

  private RadioGroup group;
  private RadioButton button;
  private Button execute;
  public String choice;
  public int sum;

  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.topup);
	addListenerOnButton();

  }

  public void addListenerOnButton() {
	group = (RadioGroup) findViewById(R.id.Choices);
	execute = (Button) findViewById(R.id.button1);
	execute.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
			int selected = group.getCheckedRadioButtonId();
			// find the radiobutton by returned id
		        button = (RadioButton) findViewById(selected);
				String choice =button.getText().toString().substring(1); 
				 int number = Integer.parseInt(choice);
				Intent intent = new Intent(TopUp.this, recharge.class);
         	   intent.putExtra("credit", number);
         	  startActivity(intent);
		}
	});
	}
}

  
