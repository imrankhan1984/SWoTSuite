package swotsuite.pubsubmiddleware;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;



import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class mqttsubscriber  implements MqttCallback {
	MqttClient client;
	static double tempValue;
	static String unitOfMeasurement;
	
	public mqttsubscriber() {
	}
	
	public static void main(String[] args) {
		new mqttsubscriber().doDemo();
	}
	
	public void doDemo() {
		try {
			client = new MqttClient("tcp://192.168.0.101:1883", MqttClient.generateClientId(), new MemoryPersistence());
			client.connect();
			client.setCallback(this);
			client.subscribe("tempMeasurement");
/*			MqttMessage message = new MqttMessage();
			message.setPayload("Hi from MQTT".getBytes());
			client.publish("foo", message);
*/
			} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
        JsonObject jsonObject = new JsonParser().parse(message.toString()).getAsJsonObject();
        tempValue= jsonObject.get("tempValue").getAsDouble();	
        unitOfMeasurement= jsonObject.get("unitofMeasurement").getAsString();
		System.out.println("received tempValue"+tempValue);
		swotsuite.application.main.Main.processdata();
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}
	
}
