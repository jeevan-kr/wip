set.seed(32297)

d <- data.frame(x=runif(100), y = runif(100))

clus <- kmeans(d, centers=5)
d$cluster <- clus$cluster

library('ggplot2'); library('grDevices');

h <- do.call(rbind, 
             lapply(
               unique(clus$cluster),
               function(c) {
                             f <- subset(d, cluster==c); f[chull(f),]
                           }
               )
             )
# Visualize the clusters
ggplot() + 
  geom_text(data = d, aes(label = cluster, x=x, y=y, color=cluster), size = 3) +
  geom_polygon(data = h, aes(x = x, y = y, group = cluster, fill = as.factor(cluster)), alpha = 0.4, linetype = 0) +
  theme(legend.position = "none")

# List the number of points
table(d$cluster)

# Intra-cluster vs. cross-cluster distances
library('reshape2')

n <- dim(d)[[1]]

pairs <- data.frame (
  ca = as.vector(outer(1:n,1:n, function(a,b) d[a, 'cluster'])),
  cb = as.vector(outer(1:n,1:n, function(a,b) d[b, 'cluster'])),
  dist = as.vector(outer(1:n,1:n, function(a,b) sqrt((d[a,'x']-d[b,'x'])^2 + (d[a,'y']-d[b,'y'])^2)))
  
  )
head(pairs)

dcast(pairs, ca~cb, value.var='dist', mean)
