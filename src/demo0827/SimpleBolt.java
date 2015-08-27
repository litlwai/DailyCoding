package demo0827;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class SimpleBolt extends BaseBasicBolt {

	@Override
	public void execute(Tuple arg0, BasicOutputCollector arg1) {
		String msg = arg0.getString(0);
		if(msg != null){
			arg1.emit(new Values(msg + " is DONE!"));
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		arg0.declare(new Fields("infoBOLT"));
	}

}
