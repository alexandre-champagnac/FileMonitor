# FileMonitor

## Contexte de l’exercice :  

On cherche à créer une application capable de surveiller l’ancienneté de fichiers dans un répertoire placé
en paramètre. Si ces fichiers sont présents depuis trop longtemps dans le répèrtoire au lieu d’avoir était transféré ailleurs, l’application envoie un mail au service informatique nommant les fichiers stagnant.




## Language utilisé : 
L’intégralité de l’application a été développé en Java. Elle utilise notamment la librairie JMailer pour envoyer les mails comme précédemment détaillé.

## Objectif du projet : 
Automatiquement détecté lors d’une défaillance du transfert de fichiers entre les répertoires pour ne plus avoir à régulièrement vérifié humainement. 

Les différentes grandes sections du projet : 

Config : Configuration Samba et différents paramètres contenant les donnés sensibles telles que les valeurs d’accés au smtp.

Inputs :Contient les deux types de Monitorings, l’un concernant une base de données, l’autre concernant un répertoire, sachant que l’objectif était de se concentrer le plus possible sur la surveillance de répertoire.

Mail : La section concernant la partie signalisation au service informatique par message. 
 






 



## Remerciement : 
Je remercie tout particulièrement mon maitre de stage pour m’avoir accompagné et beaucoup appris avec ce projet. J’ai pu réunir et concrétisait les compétences travaillés tout au long de la première année pour le réalisé.




