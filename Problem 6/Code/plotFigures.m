%%plot MST
pred = load('pred.txt');
nodes = load('nodes.txt');
len = length(pred);
pred = pred + 1;
coor = [1:len;pred']';
ind1 = sub2ind([len,len],1:len,pred');
ind2 = sub2ind([len,len],pred',1:len);
adj = zeros(len);
adj([ind1,ind2]) = 1;
figure;
title('Minimum Spanning Tree of 100 nodes');
scatter(nodes(:,1),nodes(:,2),'r');
hold on;
gplot(adj,nodes);
hold on;
%plot(path(:,1),path(:,2),'g');

axis equal;
%% plot TSP rounte
nodes = load('nodes.txt');
order = load('order.txt');
order = order + 1;
path = nodes(order,:);
figure;
scatter(nodes(:,1),nodes(:,2),'r');hold on;
plot(path(:,1),path(:,2));
%% plot TSP rounte for realworld problem
path = load('path.txt');
figure;
scatter(path(:,2),path(:,3),'r');
hold on;
plot(path(:,2),path(:,3),'b');
hold on;
title('Travelling Salesman Route in West Sahara');
hold off;