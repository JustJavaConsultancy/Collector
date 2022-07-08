export interface ISubscriberWallet {
  name?: string | null;
  walletNumber?: string | null;
  amount?: string | null;
}

export const defaultValue: Readonly<ISubscriberWallet> = {};
