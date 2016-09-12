# Logistic Regression

#read input from the tab seperated values
spamD <- read.table('spamD.tsv', header = T, sep = '\t')

#split data for training
spamTrain <- subset(spamD, spamD$rgroup >=10)

spamTest <- subset(spamD, spamD$rgroup <10)

spamVars <- setdiff(colnames(spamD), list('rgroup', 'spam'))

spamFormula <- as.formula(paste('spam=="spam"', paste(spamVars, collapse = ' + '), sep = ' ~ '))

spamModel <- glm(spamFormula, family = binomial(link='logit'), data = spamTrain)
summary(spamModel)

spamTrain$pred <- predict(spamModel, newdata = spamTrain, type = 'response')

spamTest$pred <- predict(spamModel, newdata = spamTest, type = 'response')

#Confusion Matrix
print(with(spamTest, table(y=spam, glmPred=pred>0.5)))

#Double Density Plot
library (ggplot2)
ggplot(data=spamTest) + geom_density(aes(x=pred, color = spam, linetype=spam))

#AUC
library(ROCR)
eval <- prediction(spamTest$pred, spamTest$spam)

plot(performance(eval, "tpr", "fpr") )
print(attributes(performance(eval, 'auc'))$y.values[[1]])

#Log likelihood
sum(ifelse(spamTest$spam == 'spam', log(spamTest$pred), log(1-spamTest$pred) ))


sum(ifelse(spamTest$spam == 'spam', log(spamTest$pred), log(1-spamTest$pred)))/dim(spamTest)[[1]]

#null model's LogLikelihood
pNull <- sum(ifelse(spamTest$spam == 'spam',1,0))/dim(spamTest)[[1]]

sum(ifelse(spamTest$spam=='spam',1,0)) *log(pNull) + sum(ifelse(spamTest$spam=='spam',0,1)) *log(1-pNull)

#entropy
entropy <- function(x) {
  xpos <- x[x>0]
  scaled <- xpos/sum(xpos)
  sum(-scaled*log(scaled,2))
}

print(entropy(table(spamTest$spam)))

conditionalEntropy <- function(t) {
  (sum(t[,1])*entropy(t[,1]) + sum(t[,2])*entropy(t[,2]))/sum(t)
}

print(conditionalEntropy(cM))
