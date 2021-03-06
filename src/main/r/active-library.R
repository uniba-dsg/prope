ds = read.csv("c:/workspaces/results-2.csv", sep=";")
one <- subset(ds, NServ == 1)
two <- subset(ds, NServ == 2)
five <- subset(ds, NServ == 5)
library(Rcmdr)

numSummary(one$DDS,statistics=c("mean","sd"))
numSummary(one$EPC,statistics=c("mean","sd"))
numSummary(one$DE,statistics=c("mean","sd"))

numSummary(two$DDS,statistics=c("mean","sd"))
numSummary(two$EPC,statistics=c("mean","sd"))
numSummary(two$DE,statistics=c("mean","sd"))

numSummary(five$DDS,statistics=c("mean","sd"))
numSummary(five$EPC,statistics=c("mean","sd"))
numSummary(five$DE,statistics=c("mean","sd"))

fit <- lm(ds$NServ ~ ds$DDS)
summary(fit)
layout(matrix(c(1,2,3,4),2,2))
plot(fit)

# scatterplot
plot(ds$DDS, ds$NServ, xlab="Deployment Descriptor Size", ylab="Number of Services",cex=1.5, cex.lab=1.5, cex.axis=1.5, cex.main=1.5, cex.sub=1.5)
abline(lm(ds$NServ~ds$DDS), lwd=2) 