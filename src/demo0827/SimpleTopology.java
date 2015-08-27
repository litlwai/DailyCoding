package demo0827;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

public class SimpleTopology {
	public static void main(String[] args) {
		try {
			TopologyBuilder builder = new TopologyBuilder();
			builder.setSpout("source", new SimpleSpout());
			builder.setBolt("info", new SimpleBolt(), 3).shuffleGrouping("source");

			Config config = new Config();
			config.setDebug(true);

			if (args != null && args.length > 0) {
				config.setNumWorkers(1);
				StormSubmitter.submitTopology(args[0], config, builder.createTopology());
			} else {
				config.setMaxTaskParallelism(1);
				LocalCluster cluster = new LocalCluster();
				cluster.submitTopology("Simple", config, builder.createTopology());
			}
		} catch (AlreadyAliveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTopologyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
