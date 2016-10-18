package zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by kevinmonster on 16/10/18.
 */
public class ZooKeeper_GetChildren_API_Sync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private static ZooKeeper zk = null;

    public static void main(String[] args) throws Exception {
        String path = "/zk-book4";
        zk = new ZooKeeper("192.168.99.100:2181",
                5000,
                new ZooKeeper_GetChildren_API_Sync_Usage());

        connectedSemaphore.await();

        zk.create(path,"caizheng".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);

        zk.create(path + "/c1",
                "caizheng-child1".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);


        List<String> childrenList = zk.getChildren(path, true);
        System.out.println(childrenList);

        zk.create(path + "/c2",
                "caizheng-child2".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);

        Thread.sleep(5000);

        zk.create(path + "/c3",
                "caizheng-child2".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);

        Thread.sleep(5000);
    }

    @Override
    public void process(WatchedEvent event) {
        if(Event.KeeperState.SyncConnected == event.getState()) {
            if(Event.EventType.None == event.getType() &&
                    null == event.getPath()){
                connectedSemaphore.countDown();
            } else if(event.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    System.out.println("ReGet child:" + zk.getChildren(event.getPath(), true));
                } catch (Exception e) {}
            }
        }
    }
}
