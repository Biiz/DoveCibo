# DoveCiboGit

Il `master` è protetto (come backup diciamo), utilizzare `un altro branch` o fare altri branch. Non bisogna lavorare allo stesso file contemporaneamente altriment si generano conflitti a non finire. Può essere furbo creare i branch con in nome la data.
---

Scaricate `git bash`, io uso questa [git-scm](https://git-scm.com/downloads) e probabilmente vi servirà avere [GitHub Desktop](https://desktop.github.com) ...io (Biz) ho scaricato entrambe...

Predi il link dal sito per generare il file fisico sul tuo pc:
```sh
    git clone <link> 
```

---

Per vedere il contenuto e scegliere il progetto:
```sh
    ls
    cd <nome progetto>
```

---

Sequenza per uppare le modifiche:
```sh
    git pull
	git add <file modificati>
	git commit -m "messaggio"
	git push
```
  - il `pull` iniziale è necessario prima di un `push`
  - `<file modificati>` vuole `nome.estensione` di tutti i file che sono stati modificati: se sono molti conviene usare il comando `git add -A` così da aggiungerli tutti
  - `"messaggio"` va messo tra virgolette e deve essere esauriente

---

Creare un branch (copia del progetto originale) e renderlo effettivo:
```sh
    git branch <nome branch> 
    git push --set-upstream origin <nome branch> 
```		 

---

Per navigare tra i branch
```sh
    git checkout master
	git checkout branch
	git checkout branch1 
```		

 - La directory per un progetto è sempre la stessa per tutti i branch. Quindi prima di cambiare branch è bene salvare le modifiche del branch su cui si sta lavorando altrimenti andranno perse.

---

Per vedere i comandi possibili
```sh
    git
```	

---
---

### Todos
- Fare una *servlet* che pesca dal database e manda a profiloUtente.jsp oppure usare i cookies/session
- Dopo che profiloUtente.jsp ha ricevuto i dati, uno *script* li posiziona dentro al form-input field e permette di modificarli rinviandoli poi così nel database. Attualmente esistono i seguenti metodi:
	- inserisciAccount(User u),
	- nicknameEsistente(String nik),
	- emailEsistente(String em),
	- modificaAccount(User u),
	- accedi(User u),
	- cercaUser_perId(User u).
- Come fare modificaRistorante.jsp? Forse uguale a modificaProfilo. Come pescare le info? Forse è meglio avere un solo bottone: *il tuo ristorante* dal quale si ha la panoramica del proprio ristorante. Dentro al quale c'è anche un bottone che esporta tutti i campi scritti e li mette negli input field di un clone di aggiungiRistorante
- Caratteri strani (es: euro) nel database non vengono interpretati correttamente
- Aggiunto *role* nella tabella USER (vedere se serve aggiungerlo in giro)
- Password criptata

## Todos completati
- NavBar che nella pagina aggiungiRistorante.jsp e modificaRistorante.jsp non funziona.
	SOLUZIONE: 
	- Tutti i link a librerie & eventuali devo essere in "http" (NO https),
	- Rimuovere "&callback=initMap" (aggiungiRistoranteMap.jsp),
	- Rimuovere "<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>" (alla fine di aggiungiRistoranteSliderTime.jsp),
	- Rimuovere "<link rel="stylesheet" href="homepageCSS.css"> (navBar.jsp),
- "include<navBar>" va posizionato nel <body> (NO nella <head>). Questo nel caso di aggiungiRistorante.jsp e modificaRistorante.jsp causa la scomparsa dello slider "Fascia di Prezzo".
	SOLUZIONE: Creare "aggiungiRistoranteSliderPrice.jsp" contenente il codice dello slider.
