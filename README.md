# DoveCiboGit

per vedere il contenuto
	ls
scegliere il progetto
	cd

---
Predi il link dal sito per generare il file fisico sul tuo pc
	git clone <link> 

---
Sequenza per uppare le modifiche:
	git pull
	git add <file modificati>
	git commit -m "messaggio"
	git push

	nb: il "pull" iniziale è necessario prima di un "push"
	nb: <file modificati> vuole "nome.estensione" di tutti i file che sono stati modificati: se sono molti conviene usare il comando "git add -A" così da aggiungerli tutti
	nb: "messaggio" va messo tra virgolette e deve essere esauriente

---
Creare un branch (copia del progetto originale)
	git branch <nome del branch>

Per navigare tra i branch
	git checkout master
	git checkout branch
	git checkout branch1

	nb: la directory per un progetto è sempre la stessa per tutti i branch. Quindi prima di cambiare branch è bene salvare le modifiche del branch su cui si sta lavorando altrimenti andranno perse

---
Per vedere i comandi possibili
	git 
