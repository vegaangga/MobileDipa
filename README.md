## My Cash Book-Android
Aplikasi sederhana yang dapat membantu pengguna mencatat pemasukan dan pengeluaran. Aplikasi ini dibangun sebagai syarat uji kompeteni Mobile Politeknik Negeri Malang 2023

## Deskripsi Aplikasi
My Cash Book adalah sebuah aplikasi sederhana yang dapat membantu pengguna mencatat pemasukan dan pengeluaran. Secara garis besar, aplikasi ini hanyalah aplikasi baca dan tulis data sederhana. Data disimpan di penyimpanan lokal ponsel pengguna yaitu di SQLite. Dengan menggunakan aplikasi ini, user akan dapat menyimpan tanggal, nominal, dan keterangan dari pemasukan maupun pengeluaran mereka. Setiap pengeluaran dan pemasukan kemudian akan ditampilkan totalnya.

## Fitur
* SQLite – sebagai database untuk menyimpan data user dan data catatan pengeluaran/pemasukan
* Recycler View – menampilkan data dalam bentuk list, diterapkan pada halaman Detail Cash Flow
* Date Picker – untuk mempermudah user memilih tanggal, diterapkan pada halaman tambah catatan dan edit catatan.
* Splash Screen – untuk memberi tampilan ketika sedang proses loading membuka aplikasi agar lebih menarik

## Menu Aplikasi
### 1. Login
Sebelum menggunakan aplikasi, user harus login terlebih dahulu dengan menginputkan email dan password yang telah terdaftar. Jika user belum memiliki akun, user harus mendaftar terlebih dahulu pada halaman register dengan klik tombol "Create Account"

### 2. Register
Pada halaman ini user bisa membuat akun untuk masuk ke aplikasi, user harus menginputkan username, email, password dan konfirmasi password. Ketika ada kolom yang belum diisi, email tidak sesuai dengan format, atau kolom konfirmasi password tidak bernilai sama dengan password maka akan  muncul notifikasi.
Jika user telah menginputkan data sesuai ketentuan klik tombol register, maka akun akan dibuat dan halaman akan otomatis kembali ke halaman login lagi

### 3. Halaman Beranda
Ada label yang menunjukkan total "pengeluaran" dan "pemasukan" yang telah disimpan oleh pengguna. Selain itu, pada halaman beranda juga terdapat 4 menu pilihan yaitu Pemasukan, Pengeluaran, Cash Flow, dan Pengaturan.

### 4. Menu Pemasukan
Menu ini digunakan untuk menambah data pemasukan.

### 5. Menu Pengeluaran
Menu ini digunakan untuk menambah data pengeluaran.

### 6. Menu Cash Flow
Menu ini menampilkan semua data pemasukan maupun pengeluaran berupa list

### 7. Menu Pengaturan
Pada menu ini, kita bisa mengganti password dan juga terdapat informasi tentang data pribadi saya sebagai developer aplikasi ini.

### 8. Button Logout
Terakhir, user dapat keluar dari akunnya dengan klik tombol “logout” pada halaman beranda.











