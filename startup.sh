export LC_CTYPE=en_US.UTF-8
export LC_ALL=en_US.UTF-8
apt install systemd
cp /root/mvp-frontend/dyno-frontend.service /etc/systemd/system/dyno-frontend.service
cp /root/mvp-backend/dyno-backend.service /etc/systemd/system/dyno-backend.service
cp /root/mvp-backend/ipfs.service /etc/systemd/system/ipfs.service
cp /root/mvp-backend/ganache.service /etc/systemd/system/ganache.service
ipfs config Addresses.API "/ip4/0.0.0.0/tcp/5001"
ipfs config Addresses.Gateway "/ip4/0.0.0.0/tcp/8081"
systemctl enable dyno-frontend
systemctl enable dyno-backend
systemctl enable ipfs
systemctl enable ganache
systemctl start dyno-frontend
systemctl start dyno-backend
systemctl start ipfs
systemctl start ganache