package swotsuite.pubsubmiddleware;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import swotsuite.framework.m3Struct;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class mqttSubscriber implements MqttCallback {
	MqttClient client;
	public static double tempValue;
	static String unitOfMeasurement;

	public mqttSubscriber() {
	}

	public static void main(String[] args) {
		new mqttSubscriber().doDemo();
	}

	public void doDemo() {
		try {
			client = new MqttClient("tcp://192.168.0.103:1883",
					MqttClient.generateClientId(), new MemoryPersistence());
			client.connect();
			client.setCallback(this);
			client.subscribe("tempMeasurement");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
		JsonObject jsonObject = new JsonParser().parse(message.toString())
				.getAsJsonObject();
		tempValue = jsonObject.get("tempValue").getAsDouble();
		unitOfMeasurement = jsonObject.get("unitofMeasurement").getAsString();
		System.out.println("received temperatureSensor measurement");

		swotsuite.senML.senMLData.writingsenML();

		swotsuite.application.main.Main.processdata();

	    m3Struct m3data = new m3Struct(swotsuite.application.main.Main.suggestion, swotsuite.application.main.Main.deduceValue);
	    swotsuite.pubsubmiddleware.wsPublisher.senData(m3data);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}

}
