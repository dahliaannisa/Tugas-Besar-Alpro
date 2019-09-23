package tubes;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ListBarang {
	
	StringBuilder sb = new StringBuilder();
	static Scanner sc = new Scanner(System.in);
	BufferedInputStream bis = null;
	FileInputStream fis = null;
	char eof = '.';
    String[] dump = new String[panjangArray];
	Barang[] br = new Barang[panjangArray];
	static int panjangArray = 2;
	static String idBarang;
	static String namaBarang;
	static int harga;
	static int jumlah;
	static String jenisBarang;
	static String merk;
	
//	public void panjangData() {
//		System.out.println("Masukkan Panjang Data : ");
//		panjang = sc.nextInt();
//		br = new Barang[panjang];
//		dump = new String[panjang];
//	}
//	
//	public void tulisFile() {
//		String idBarang;
//		String namaBarang;
//		int harga;
//		String jenisBarang;
//		int jumlah;
//		String merk;
//		try {
//			FileWriter fw = new FileWriter("ListBarang.txt", true);
//			for (int i = 0; i < dump.length; i++) {
//				System.out.println("Masukkan idBarang : ");
//				idBarang = sc.next();
//				fw.write(idBarang + "~");
////				br[i] = new Barang();
//				System.out.println("Masukkan nama Barang : ");
//				namaBarang = sc.next();
//				fw.write(namaBarang + "~");
////				br[i].setNamaBarang(namaBarang);
//				System.out.println("Masukkan Harga Barang : ");
//				harga = sc.nextInt();
//				fw.write(harga + "~");
////				br[i].setHarga(harga);
//				System.out.println("Masukkan jenis Barang : ");
//				jenisBarang = sc.next();
//				fw.write(jenisBarang + "~");
////				br[i].setJenisBarang(jenisBarang);
//				System.out.println("Masukkan Jummlah Barang : "); 
//				jumlah = sc.nextInt();
//				fw.write(jumlah + "~");
////				br[i].setJumlahBarang(jumlah);
//				System.out.println("Masukkan Merk Barang : ");
//				merk = sc.next();
//				fw.write(merk + " ");
//				fw.flush();
//			}
//			fw.write(".");
//			fw.close();
//			System.out.println("Data berhasil ditambahkan");
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		}
//		
	// }
	
	public void bacaFile() {
		try {
			fis = new FileInputStream("ListBarang.txt");
			bis = new BufferedInputStream(fis);
		}catch (IOException e) {
			System.err.println("Error I/O");
		}	
	}
	
	public void loadFile() {
		int data = -1;
		char ch;
		int index = 0;
		try {
			if(bis != null) {
				data = bis.read();
				ch = (char) data;
				while(ch != eof && data != -1) {
					if (ch != ' ') {
						sb.append(ch);
					}else {
						dump[index] = sb.toString();
						index++;
						sb = new StringBuilder();
					}
					ch = (char) bis.read();
				}
				System.out.println();
			}
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void closeFile() {
		try {
			bis.close();
		}catch (IOException e) {
			System.out.println("Error I/O");
		}
	}
	
	public void ambilData() {
		bacaFile();
		loadFile();
		closeFile();
		System.out.println("===============Ambil Data====================");
		String []temp;
		for(int a=0; a <dump.length; a++) {
			temp = dump[a].split("~");
			br[a] = new Barang();
			br[a].setIdBarang((temp[0]));
			br[a].setNamaBarang(temp[1]);
			br[a].setHarga(Integer.parseInt(temp[2]));
			br[a].setJenisBarang(temp[3]);
			br[a].setJumlahBarang(Integer.parseInt(temp[4]));
			br[a].setMerk(temp[5]);
		}
		System.out.println("Selesai");
	}
	
	public void tampilData() {
		System.out.println("=============Tampil Data================");
		int urut = 0;
		for(int a=0; a<dump.length; a++) {
			urut++;
			System.out.println("Barang ke- " +urut);
			System.out.println("IDBarang : " + br[a].getIdBarang());
			System.out.println("NamaBarang : "+ br[a].getNamaBarang());
			System.out.println("Harga : "+ br[a].getHarga());
			System.out.println("JenisBarang : "+br[a].getJenisBarang());
			System.out.println("JumlahBarang : "+br[a].getJumlahBarang());
			System.out.println("Merk : "+br[a].getMerk());
		}
	}
	
	public void ubahNama() {
		System.out.println("=============Ubah Nama=================");
		System.out.println("Nama yang akan diubah");
		String cari = sc.next();
		String baru;
		for(int a = 0; a < br.length; a++) {
			if(br[a].getNamaBarang().contains(cari)) {
				System.out.println("nama barang ditemukan"+ br[a].getNamaBarang());
				System.out.println("silahkan masukan nama baru :");
				baru = sc.next();
				br[a].setNamaBarang(baru);
				System.out.println("ubah data sukses");
			}
		}
		updateFile();
	}
	
	public void updateFile() {
		try {
			FileWriter f = new FileWriter("ListBarang.txt", false);
			String text;
			sb = new StringBuilder();
			int last = br.length -1;
			for (int a= 0; a <br.length; a++) {
				sb.append(br[a].getIdBarang() + "~" + br[a].getNamaBarang() + "~" + br[a].getHarga() + "~" + br[a].getJenisBarang() + "~" + br[a].getJumlahBarang() + "~" + br[a].getMerk());
				if (a != last) {
					sb.append(" ");
				}
			}
			sb.append(" .");
			text = sb.toString();
			
			f.write(text);
			f.flush();
			
			f.close();
		}catch (IOException io) {
			System.out.println("Error IO");
		}
	}
	

	public void hapusData() {
		System.out.println("=============Hapus Data=================");
		System.out.println("Nama Data yang akan dihapus");
		String cari = sc.next();
		String hapus;
		String y = "Y";
		
		for(int a = 0; a < br.length; a++) {
			if(br[a].getIdBarang().equalsIgnoreCase(cari)) {
				System.out.println("nama barang ditemukan "+ br[a].getIdBarang());
				System.out.println("Apakah anda Yakin ingin Menghapus Data? Y/N");
				hapus = sc.next();
				if (hapus.equals(y)) {
					br[a].setNamaBarang(null);
					br[a].setJenisBarang(null);
					br[a].setJumlahBarang(0);
					br[a].setMerk(null);
					br[a].setHarga(0);
					System.out.println("Hapus data sukses");
				} else {
					System.out.println("Kembali ke halaman Menu");
				}
			}
		}
		updateFile();
	}
	
	
	
	public void tambahData() {
		int panjangBerubah;
		panjangBerubah = panjangArray;
		try {
			FileWriter fw = new FileWriter("ListBarang.txt", false);
			for (int i = 0; i < 2; i++) {
				System.out.println("Masukkan id Barang yang akan ditambahkan : ");
				idBarang = sc.next();
				fw.write(idBarang + "~");
				fw.flush();
				System.out.println("Masukkan nama Barang yang akan ditambahkan : ");
				namaBarang = sc.next();
				fw.write(namaBarang + "~");
				fw.flush();
				System.out.println("Masukkan Harga Barang yang akan ditambahkan :  ");
				harga = sc.nextInt();
				fw.write(harga + "~");
				fw.flush();
				System.out.println("Masukkan jenis Barang yang akan ditambahkan :  ");
				jenisBarang = sc.next();
				fw.write(jenisBarang + "~");
				fw.flush();
				System.out.println("Masukkan Jumlah Barang yang akan ditambahkan :  "); 
				jumlah = sc.nextInt();
				fw.write(jumlah + "~");
				fw.flush();
				System.out.println("Masukkan Merk Barang yang akan ditambahkan :  ");
				merk = sc.next();
				fw.write(merk + " ");
				fw.flush();
			}

			fw.close();
			br = new Barang[panjangBerubah++];
			dump = new String[panjangBerubah++];
			updateTambahFile();
			ambilData();
		
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateTambahFile() {
		try {
			FileWriter f = new FileWriter("ListBarang.txt", true);
			String text;
			sb = new StringBuilder();
			int last = br.length -1;
			for (int a= 0; a <br.length; a++) {
				sb.append(br[a].getIdBarang() + "~" + br[a].getNamaBarang() + "~" + br[a].getHarga() + "~" + br[a].getJenisBarang() + "~" + br[a].getJumlahBarang() + "~" + br[a].getMerk());
				if (a != last) {
					sb.append(" ");
				}
			}
			sb.append(" .");
			text = sb.toString();
			
			f.write(text);
			f.flush();
			f.close();
		}catch (IOException io) {
			System.out.println("Error IO");
		}
	}
	
	
	public int pilihMenu() {
		int menu;
		System.out.println("================Selamat Datang=================");
		System.out.println("Silahkan pilih menu yang diinginkan :"+"/\n 1. Tulis Data"+"\n 2. Ambil Data"+ "\n 3. Tampil Data"+"\n 4. Ubah Data"+"\n 5. Hapus Data"+ "\n 6. Tambah Data" + "\n 99. keluar");
		menu = sc.nextInt();
		return menu;
	}
	
	public static void main(String[] args) {
		ListBarang lb = new ListBarang();
		int menu;
		boolean ulang = true;
		
		while (ulang) {
			menu = lb.pilihMenu();
			switch(menu) {
			case 1:
//				lb.panjangData();
//				lb.tulisFile();
				break;
			case 2:
				lb.ambilData();
				break;
			case 3:
				lb.tampilData();
				break;
			case 4:
				lb.ubahNama();
				break;
			case 5:
				lb.hapusData();
				break;
			case 6:
				lb.tambahData();
				break;
			case 99:
				System.out.println("Selamat Tinggal");
				ulang = false;
				break;
			default:
				System.out.println("Menu belum terdapat");
				break;
			}
		}
	}
	
}
