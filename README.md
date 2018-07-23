# mvp-backend
MVP Backend

Run:
java -jar mvp-0.0.1-SNAPSHOT.jar --spring.profiles.active=demo

IPFS Setup:
1) wget https://dist.ipfs.io/go-ipfs/v0.4.16/go-ipfs_v0.4.16_linux-amd64.tar.gz
2) ./install.sh
3) ipfs init
4) ipfs daemon --enable-pubsub-experiment
5) http://localhost:5001/webui
6) Optional change of config (~/.ipfs/config)