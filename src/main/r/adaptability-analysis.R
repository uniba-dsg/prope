#load metric data; adjust this to the path of the result file written by prope
analysisFile <- "c:/workspaces/results.csv"
rawDataFile <- "c:/workspaces/raw.csv"

input = read.csv(analysisFile, sep=";")
library(Rcmdr)

ds <- subset(input, input$group == "19-05-2014")
ds2 <- subset(input, input$group == "10-10-2014")

# sanity checks
nrow(ds)
nrow(ds2)
colnames(ds)
prop.table(summary(ds$isCorrectNamespace))
prop.table(summary(ds$referencesIssuesFound))
prop.table(summary(ds$isExecutable))

# reduced data set
sane <- subset(ds,ds$isCorrectNamespace=='true' & ds$referencesIssuesFound=='false')

nrow(sane)
prop.table(summary(sane$isExecutable))
summary(sane$isExecutable)

# plot element occurences
constructs = read.csv(rawDataFile, sep=";")
constructsSorted = constructs[order(constructs$number),]
constructsRemoved = constructsSorted[(constructsSorted$number > 150),]
constructsRemoved$number <- (constructsRemoved$number / nrow(ds)) * 100
list(constructsRemoved)
par(mar=c(6, 12, 4, 2) + 0.1)
barplot(constructsRemoved$number, horiz=TRUE,names.arg=constructsRemoved$element, las=1,cex.names=1.5, cex.lab=2, xlab="Percentage of Processes", ylab="", xlim=c(0,100))

#plot with less constructs
constructsRemoved = constructsSorted[(constructsSorted$number > 300),]
constructsRemoved$number <- (constructsRemoved$number / nrow(ds)) * 100
list(constructsRemoved)
par(mar=c(6, 12, 4, 2) + 0.1)
barplot(constructsRemoved$number, horiz=TRUE,names.arg=constructsRemoved$element, las=1,cex.names=1.5, cex.lab=1.5, xlab="Percentage of Processes", ylab="", xlim=c(0,100))


#group in executable and non-executable
exec <- subset(sane, sane$isExecutable=='true')
nonExec <- subset(sane, sane$isExecutable=='false')
nrow(exec)
nrow(nonExec)
list(exec)

# compute descriptive metric data
summary(exec$AD0.2)
sd(exec$AD0.2, na.rm=TRUE)
summary(exec$AD0.4)
sd(exec$AD0.4, na.rm=TRUE)
summary(exec$AD0.6)
sd(exec$AD0.6, na.rm=TRUE)
summary(exec$AD0.8)
sd(exec$AD0.8, na.rm=TRUE)
summary(exec$wAD)
sd(exec$wAD, na.rm=TRUE)

summary(nonExec$AD0.2)
sd(nonExec$AD0.2, na.rm=TRUE)
summary(nonExec$AD0.4)
sd(nonExec$AD0.4, na.rm=TRUE)
summary(nonExec$AD0.6)
sd(nonExec$AD0.6, na.rm=TRUE)
summary(nonExec$AD0.8)
sd(nonExec$AD0.8, na.rm=TRUE)
summary(nonExec$wAD)
sd(nonExec$wAD, na.rm=TRUE)

# check if the sets are normally distributed (Shapiro-Wilk)
shapiro.test(exec$AD0.2)
shapiro.test(exec$AD0.4)
shapiro.test(exec$AD0.6)
shapiro.test(exec$AD0.8)
shapiro.test(exec$wAD)

shapiro.test(nonExec$AD0.2)
shapiro.test(nonExec$AD0.4)
shapiro.test(nonExec$AD0.6)
shapiro.test(nonExec$AD0.8)
shapiro.test(nonExec$wAD)

# check for difference in the distributions (Mann-Whitney U)
wilcox.test(exec$AD0.2, nonExec$AD0.2, alternative="greater")
wilcox.test(exec$AD0.4, nonExec$AD0.4, alternative="greater")
wilcox.test(exec$AD0.6, nonExec$AD0.6, alternative="greater")
wilcox.test(exec$AD0.8, nonExec$AD0.8, alternative="greater")
wilcox.test(exec$wAD, nonExec$wAD, alternative="greater")

# compute amount of unique values
length(unique(exec$AD0.2)) / nrow(exec)
length(unique(exec$AD0.4)) / nrow(exec)
length(unique(exec$AD0.6)) / nrow(exec)
length(unique(exec$AD0.8)) / nrow(exec)
length(unique(exec$wAD)) / nrow(exec)

length(unique(nonExec$AD0.2)) / nrow(nonExec)
length(unique(nonExec$AD0.4)) / nrow(nonExec)
length(unique(nonExec$AD0.6)) / nrow(nonExec)
length(unique(nonExec$AD0.8)) / nrow(nonExec)
length(unique(nonExec$wAD)) / nrow(nonExec)

# correlation between wAD and AD0.6
summary(lm(exec$AD0.6 ~ exec$wAD))
summary(lm(nonExec$wAD ~ nonExec$AD0.6))

# compare small and large processes
summary(sane$elements)
small <- subset(ds,ds$elements < 7)
large <- subset(ds,ds$elements > 15)
nrow(small)
nrow(large)
wilcox.test(small$wAD,large$wAD, alternative="greater")

summary(exec$elements)
smallExec <- subset(exec,exec$elements < 10)
largeExec <- subset(exec,exec$elements > 50)
nrow(smallExec)
nrow(largeExec)
summary(smallExec$AD0.6)
summary(largeExec$AD0.6)
wilcox.test(smallExec$AD0.6,largeExec$AD0.6, alternative="greater")
summary(smallExec$wAD)
summary(largeExec$wAD)
wilcox.test(smallExec$wAD,largeExec$wAD, alternative="greater")

summary(nonExec$elements)
smallNonExec <- subset(nonExec,nonExec$elements < 7)
largeNonExec <- subset(nonExec,nonExec$elements > 13)
nrow(smallNonExec)
nrow(largeNonExec)
summary(smallNonExec$AD0.6)
summary(largeNonExec$AD0.6)
wilcox.test(smallNonExec$AD0.6,largeNonExec$AD0.6, alternative="greater")
summary(smallNonExec$wAD)
summary(largeNonExec$wAD)
wilcox.test(smallNonExec$wAD,largeNonExec$wAD, alternative="greater")

#group in executable and non-executable for second set
sane2 <- subset(ds2,ds2$isCorrectNamespace=='true' & ds2$referencesIssuesFound=='false')
nrow(sane2)
prop.table(summary(sane2$isExecutable))
summary(sane2$isExecutable)
exec2 <- subset(sane2, sane2$isExecutable=='true')
nonExec2 <- subset(sane2, sane2$isExecutable=='false')
nrow(exec2)
nrow(nonExec2)

#test if there are difference in the distributions over time
nrow(exec)
nrow(exec2)
summary(exec$AD0.6)
summary(exec2$AD0.6)
wilcox.test(exec$AD0.6,exec2$AD0.6, alternative="greater")
summary(exec$wAD)
summary(exec2$wAD)
wilcox.test(exec$wAD,exec2$wAD, alternative="greater")


nrow(nonExec)
nrow(nonExec2)
summary(nonExec$AD0.6)
summary(nonExec2$AD0.6)
wilcox.test(nonExec$AD0.6,nonExec2$AD0.6, alternative="greater")
summary(nonExec$wAD)
summary(nonExec2$wAD)
wilcox.test(nonExec$wAD,nonExec2$wAD, alternative="greater")

