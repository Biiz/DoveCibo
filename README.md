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
	git branch -a //per vederli tutti
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
- [ ] File upload foto (sta facendo giorgio)
- [ ] caricare db ~200 ristoranti (30 per iniziare)
- [ ] Filtro visualizzazione ristoranti (ricerca nome poesizione ecc)
- [ ] slider ore
- [ ] mobile friendly
- [ ] mappa ristorante da gestire
- [ ] Password cifrata
- [ ] Numero civico in aggiungiRistorante.jsp
- [ ] Notifiche in generale (foto commenti e valutazioni)
- [ ] password dimenticata
- [x] example

## Todos completati
- NavBar che nella pagina aggiungiRistorante.jsp e modificaRistorante.jsp non funziona.
	SOLUZIONE: 
	- Tutti i link a librerie & eventuali devo essere in "http" (NO https),
	- Rimuovere *callback=initMap* (aggiungiRistoranteMap.jsp),
	- Rimuovere *script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"* (alla fine di aggiungiRistoranteSliderTime.jsp),
	- Rimuovere *link rel="stylesheet" href="homepageCSS.css"* (navBar.jsp),
- *include navBar* va posizionato nel *body* (NO nella *head*). Questo nel caso di aggiungiRistorante.jsp e modificaRistorante.jsp causa la scomparsa dello slider "Fascia di Prezzo".
	SOLUZIONE: Creare "aggiungiRistoranteSliderPrice.jsp" contenente il codice dello slider.
