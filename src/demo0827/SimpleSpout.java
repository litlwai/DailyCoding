package demo0827;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class SimpleSpout extends BaseRichSpout {

	// Spout充当水龙头的作用。不断弄出数据等。

	Random random = new Random();
	SpoutOutputCollector collector;

	/**
	 * 每调用一次就可以向storm集群中发射一条数据 （一个tuple元组） 该方法会被不停的调用
	 */
	@Override
	public void nextTuple() {
		try {
			String msg = random.nextInt(500) * 1000000 + "";
			collector.emit(new Values(msg));
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
		collector = arg2;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		arg0.declare(new Fields("source"));
	}

}
